package net.coagulate.JSLBot;

import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Messages.*;
import net.coagulate.JSLBot.Packets.Packet;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.U32BE;
import net.coagulate.JSLBot.Packets.Types.U8;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Closeable;
import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

/** Handles a UDP circuit between us and a Simulator.
 * @author Iain Price
 */
public final class Circuit extends Thread implements Closeable {
    private final AtomicInteger bytesin=new AtomicInteger(0);
    private final AtomicInteger bytesout=new AtomicInteger(0);
    private Logger log;
    private int circuitsequence;
    // list of sent reliable packets we're waiting to hear an ack for
    private final Map<Packet,Date> inflight=new HashMap<>();
    // packets we have already acked and shouldn't reprocess, beyond re-acking
    private final Map<Integer,Date> acked=new HashMap<>();
    // sequence number for packets in this circuit
    private int sequence=1;
    // reference to the agent we're a circuit for
    private JSLBot bot;
    // the simulator's details
    @Nullable
    private InetSocketAddress address=null;
    private InetSocketAddress address() {
        if (address==null) { throw new IllegalStateException("Attempting to get circuit socket address but it's null, weirdly"); }
        return address;
    }
    // our listening socket / endpoint
    private DatagramSocket socket;
    // biggest remote packet we acked or something.  comes up in UDP ping messages, but i dont think we even need it
    private int highestack=0;
    // list of outstanding outbound acks
    private final List<Integer> ackqueue=new ArrayList<>();
    // last time we sent any acks, used to force manual PacketAck 
    @Nonnull
    private Date lastacks=new Date();
    // last housekeeping
    @Nonnull
    private Date lastmaintenance=new Date();
    // name of the simulator
    private String regionname=""; public String getRegionName() { return regionname; }
    // handle of the simulator
    @Nullable
    private LLUUID regionuuid=null;
    // last time we rxed anything
    @Nonnull
    private Date lastpacket=new Date();
    // the primary all important region handle
    @Nonnull
    private Long regionhandle;
    // primary CAPS url
    @Nullable
    private String capsurl;
    // primary CAPS object
    @Nullable
    private CAPS caps=null;
    // Get the CAPS object attached to this circuit's region
    @Nonnull
    public CAPS getCAPS() {
        if (caps==null) { throw new IllegalStateException("CAPS is not currently configured for this circuit"); }
        return caps;
    }
    // Region object for this circuit's region
    @Nullable
    private Regional regional;
    // target address
    @Nullable
    private String simip;
    private int simport;
    @Nonnull
    public String getSimIPAndPort() { return simip+":"+simport; }
    // how many packets sent
    private int packetrate=0;

    // clean this up

    // Have started a disconnect, so dont error so much
    private boolean disconnecting=false;
    // Have given a reason for the disconnect, so dont give any more
    private boolean disconnectlogged=false;
    // We're dead.
    private boolean disconnected=false;
    /** create a circuit given the parameters.
     * 
     * @param parent Owning JSLBot
     * @param address Target IP address
     * @param port Target port
     * @param passedregionhandle Target region handle
     * @param capsurl Target CAPS url
     */
    Circuit(@Nonnull JSLBot parent, @Nullable String address, int port, @Nullable Long passedregionhandle, @Nullable String capsurl) {
        log=parent.getLogger("Circuit."+address+":"+port);
        if (passedregionhandle==null) { throw new IllegalArgumentException("Null region handles are not allowed"); }
        circuitsequence=parent.getCircuitSequence();
        setDaemon(true);
        this.simip=address;
        this.simport=port;
        regional=new Regional(this);
        this.capsurl=capsurl;
        bot=parent;
        regionhandle=passedregionhandle;
    }
    
    private boolean hasrunconnect=false;
    /** Connect to the sim.
     * This will send initial packets and wait for them to be acknowledged.
     * Automatically starts the main circuit thread to receive said acknowledges.
     * Use me instead of run()
     * Note:  This function may take some time to return.
     * @throws IOException Failure to connect
     */
    public void connect() throws IOException {
        if (simip==null || simport==0) { throw new IllegalStateException("Sim IP + port are not initialised when connect()ing"); }
        hasrunconnect=true;
        this.address=new InetSocketAddress(simip,simport);
        socket=new DatagramSocket();
        socket.connect(this.address);
        if (Debug.CIRCUIT) { log.finer("Sending initial circuit code over socket to "+this.address); }
        send(bot.useCircuitCode());
        this.setDaemon(true);
        this.start(); // launch the rx driver
        // wait for the 'outstanding acks' to be updated (meaning our reliable UseCircuitCode is acked)
        try { synchronized(inflight) { inflight.wait(5000); } } catch (InterruptedException ex) { throw new IOException("Failed to get UseCircuitCode Ack"); }
        if (Debug.CIRCUIT) { log.finer("Outstanding ACKS: "+inflight.size()); }
        if (!inflight.isEmpty()) { throw new IOException("Login completed, UseCircuitCode sent, and not acknowledged..."); }
        log.info("Successfully connected circuit");
        if (capsurl!=null) { caps=new CAPS(this,capsurl);
            //noinspection CallToThreadRun
            caps.run();
        }
    }
    
    /** Runs the UDP receiver thread.
     * Do not call, run connect().
     */
    @Override
    public void run() {
        this.setName("Circuit for "+bot.getUsername()+" to "+this.address);
        if (!hasrunconnect) { log.severe("Must connect a new circuit"); return; }
        if (Debug.CIRCUIT) { log.fine("Starting background driver:"+this.getName()); }
        try {
            DatagramPacket receive=new DatagramPacket(new byte[Constants.UDP_MAX_BUFFER], Constants.UDP_MAX_BUFFER);            
            // begin eternal damnation as a circuit driver.
            while (!disconnected && !disconnectlogged && !bot().quitting()) {
                try {
                    socket.setSoTimeout(250);
                    socket.receive(receive);
                    // if we received nothing, go to "Socket Timeout Exception"
                    lastpacket = new Date();
                    ByteBuffer rx=ByteBuffer.allocate(receive.getLength());
                    rx.put(receive.getData(),0,receive.getLength());
                    rx.position(0);
                    if (Constants.PACKET_ACCOUNTING) { 
                        bytesin.getAndAdd(receive.getLength());
                        bot.bytesin.getAndAdd(receive.getLength());
                    }
                    Packet p;
                    p=Packet.decode(rx);
                    if (Constants.PACKET_ACCOUNTING_BY_MESSAGE) {
                        bot.accountMessageIn(p.message().getId(),receive.getLength());
                    }
                    for (Integer rxack:p.appendedacks) { receivedAck(rxack); }
                    //if (p.getReliable()) { System.out.println("ACK REQUIRED IN:"+p.getName()); }
                    //System.out.println("RX: "+p.getName());
                    processPacket(p);
                }
                catch (SocketTimeoutException e) {if (Debug.ACK) { log.finer("Exiting receive without event"); } } // as requested, and we dont care
                // timeout is just to make sure we get HERE \/ once in a while
                if ((ackqueue.size()>0 && lastAck()>2000) || ackqueue.size()>32) {
                    if (Debug.ACK) { log.finer("Manually sending ACKs"); }
                    sendAck();                    
                } else {
                    if (Debug.ACK) { log.finer("NOT sending any ACKs.  Q size is "+ackqueue.size()+" and lastAck is "+lastAck()+"ms"); }
                }
                if (lastMaintenance()>2500) { 
                    maintenance();
                }
            }
        }
        catch (SocketException ex) {
            if (!bot().quitting()) // who cares if we're closing the bot
            { 
                if (!disconnectlogged) { disconnectlogged=true; log.warning("Circuit to "+this.regionname+" has been closed, "+ex.toString()); }
                close();
            }
        }
        catch (@Nonnull RuntimeException|IOException e) {
            if (!disconnectlogged) { disconnectlogged=true; log.log(SEVERE,"Circuit driver run() loop crashed : "+e.toString(),e); }
            close();
        }
        if (Debug.CIRCUIT) { log.log(Level.FINE, "Deregistering circuit to {0}", regionname); }
        bot.deregisterCircuit(regionhandle,this);
    }
    /** When the last ack packet was sent, as an interval from now.
     * @return How long ago last acks were sent, in milliseconds
     */
    private long lastAck() {
        return new Date().getTime()-lastacks.getTime();
    }
    /** When maintenance last ran, as an interval from now
     * 
     * @return How long ago maintenance last ran, in milliseconds
     */
    private long lastMaintenance() {
        return new Date().getTime()-lastmaintenance.getTime();
    }
    /** Add a packet to the list to send ACKs for
     * @param p Packet to require ACK for
     */
    public void requiresAck(Packet p) {
        synchronized(inflight) {
            inflight.put(p,new Date());
        }
    }
    /** Handle a received ACK */
    private void receivedAck(int seqno)
    {
        Packet match=null;
        synchronized(inflight) { 
            for (Packet p:inflight.keySet()) {
                if (p.getSequence()==seqno) { match=p; }
            }
            if (match==null) { 
                log.log(Level.FINE, "Unexpected ACK {0}", seqno);
                return;
            }
            inflight.remove(match);
            inflight.notifyAll();
        }
    }
    int maintenancecounter=0;
    private boolean pinged=false;
    /** Run maintenance tasks */
    private void maintenance() {
        long interval=new Date().getTime()-lastpacket.getTime();
        if (interval>(Constants.CIRCUIT_PING*1000)) {
            if (!pinged) {
                pinged=true; 
                log.log(Level.FINE, "Circuit silent for more than {0} seconds, sending ping.", Constants.CIRCUIT_PING);
                StartPingCheck ping=new StartPingCheck();
                send(ping,true);
            }
        } else { pinged=false; } // ping when crossing threshold.  once.  reset threshold detection here.    
        if (interval>(Constants.CIRCUIT_TIMEOUT*1000)) {
            disconnectlogged=true; log.log(SEVERE, "Circuit has received no packets in {0} seconds, closing.", Constants.CIRCUIT_TIMEOUT);
            close();
            return;
        }
        // polled every 2.5s
        maintenancecounter++;
        lastmaintenance=new Date();
        if ((maintenancecounter % 12) == 0) {
            // trim the list of remember transmissions, if they didn't send after 60 seconds they're dead.
            synchronized(acked) {
                Set<Integer> removeme=new HashSet<>();
                for (Map.Entry<Integer, Date> entry : acked.entrySet()) {
                    if (((new Date().getTime())-(entry.getValue().getTime()))>60000) {
                        removeme.add(entry.getKey()); // can't remove while iterating, concurrent mod exception
                    }
                }
                for (int seq:removeme) {
                    acked.remove(seq);
                }
            }
        }
        synchronized (inflight) {
            for (Map.Entry<Packet, Date> entry : inflight.entrySet()) {
                Packet p = entry.getKey();
                // retransmit any packets that haven't been acked in a while
                Date sent= entry.getValue();
                if (packetrate<5 && ((new Date().getTime())-(sent.getTime()))>Constants.ACK_TIMEOUT) {
                    //System.out.println("In retransmit with packetrate "+packetrate);
                    log.finer("Retransmitting packet "+p.getSequence());
                    p.setResent(true);
                    send(p);
                    inflight.put(p,new Date());
                }
            }
        }
    }
    /** Send a message, optionally reliably
     * @param m Message to send
     * @param reliable Send as reliable/require ACKs
     */
    public void send(Message m,boolean reliable) { Packet p=new Packet(m); p.setReliable(reliable); send(p); }
    /* Send a message, unreliably */
    public void send(Message m) { send(m,false); }
    /* Send a packet */
    public void send(@Nonnull Packet p) {
        List<Integer> sending=new ArrayList<>(); // list of acks we'll append.
        // synchronise up around the ack queue, strip it , aka "claim it"... 
        synchronized(ackqueue) {
            for (Integer i:ackqueue) {
                sending.add(i); if (i>highestack) { highestack=i; }
            }
            ackqueue.clear();
            lastacks=new Date();
        }
        
        int acksize=0;
        // acks are 4 bytes each plus a byte 'counter'
        if (sending.size()>0) { acksize=(4*sending.size())+1; p.setAck(true);}
        // claim message+ack size
        ByteBuffer buffer=ByteBuffer.allocate(p.size()+acksize);
        if (buffer.capacity()>1500) { log.log(Level.WARNING, "Message size is {0} which is quite large", buffer.capacity()); }
        // write message to the buffer
        p.write(this,buffer);
        byte[] transmit=buffer.array();
        if (p.getZeroCode()) { transmit=BotUtils.zeroEncode(transmit); }
        // final acks aren't zero coded
        StringBuilder ackedlist= new StringBuilder();
        if (buffer.capacity()>1500) { log.log(Level.WARNING, "Message size is {0} post ZeroCoding which is quite large", sending.size()); }
        // IF sending acks, append them now (AFTER zerocoding)
        if (sending.size()>0) {
            ByteBuffer append=ByteBuffer.allocate(transmit.length+(4*sending.size())+1);
            append.put(transmit);
            // append acks
            for (Integer i:sending) {
                U32BE acknumber=new U32BE(i); // yup, the appended ones are big endian.   but a PacketAck uses little endian in the body.  LL ;)
                acknumber.write(append);
                ackedlist.append(i).append(" ");
            } // breaks if >256 acks :P
            // finally number of acks, apparently.
            U8 count=new U8(sending.size());
            count.write(append);
            lastacks=new Date();
            if (Debug.ACK) { log.log(Level.FINEST, "Appended ACKS:{0}", ackedlist.toString()); }
            transmit=append.array();
        }
        if (Debug.PACKET) { log.log(Level.FINEST, "Sending packet: {0}", p.dump());
            System.out.println();
            for (Byte b:transmit) {
                System.out.print(b.toString()+" ");
            }
            System.out.println();
        }
        if (Debug.PACKET) {
            // nice debugger, pretend we received our own packet, do we dismantle it properly?
            ByteBuffer decodeme=ByteBuffer.allocate(transmit.length);
            decodeme.put(transmit);
            decodeme.position(0);
            log.log(Level.FINEST, "Reverse engineered: {0}", Packet.decode(decodeme).dump());
        }           
        DatagramPacket packet=new DatagramPacket(transmit,transmit.length,address());
        /*if (p.getName().equals("AgentUpdate")) { 
            Exception e=new Exception("Generate stack trace");
            e.printStackTrace(); // show me!
        }*/
        //System.out.println("TX: "+p.getName());
        if (Constants.PACKET_ACCOUNTING) {
            bytesout.addAndGet(packet.getLength());
            bot.bytesout.addAndGet(packet.getLength());
        }
        if (Constants.PACKET_ACCOUNTING_BY_MESSAGE) {
            bot.accountMessageOut(p.getId(), packet.getLength());
        }
        try { socket.send(packet); }
        catch (IOException e) { log.log(SEVERE,"Error transmitting packet "+e.toString(),e); }
        packetrate++;
    }

    /** Produce a reliable sequence counter for packets
     * @return Atomicly incrementing sequence number
     */
    public synchronized int getSequence() {
        return sequence++;
    }
   
    private final Object lockdisconnecting=new Object();
    /** Close this circuit */
    @Override
    public void close() {
        synchronized(lockdisconnecting) {
            if (disconnecting) { return; }
            disconnecting=true;
        }
        LogoutRequest l = new LogoutRequest(); //debug(owner,"Logout Request exceptioned - ",e); }
        l.bagentdata.vagentid=bot.getUUID();
        l.bagentdata.vsessionid=bot.getSession();
        send(l);
        CloseCircuit p = new CloseCircuit(); //debug(owner,"Circuit Close message exceptioned - ",e); }
        send(p);
        try { socket.close(); }
        catch (Exception e) { }//debug(owner,"Socket closure exceptioned - ",e); }
        if (!disconnectlogged) { disconnectlogged=true; log.log(Level.FINE, "We have requested closure of circuit to {0}", this.regionname); }
        disconnected=true;
    }
    
    /** Send a standalone ACK packet */
    private void sendAck() {
        //packet ack generator
        List<Integer> sending=new ArrayList<>();
        synchronized(ackqueue) {
            for (Integer i:ackqueue) { sending.add(i); if (i>highestack) { highestack=i; }}
            ackqueue.clear();
        }
        if (!sending.isEmpty()) {
            PacketAck ack=new PacketAck();
            ack.bpackets=new ArrayList<>();
            StringBuilder acklist= new StringBuilder();
            for (Integer i:sending) {
                PacketAck_bPackets ackblock = new PacketAck_bPackets();
                ackblock.vid=new U32(i);
                ack.bpackets.add(ackblock);
                acklist.append(i).append(" ");
            }
            if (Debug.ACK) { log.log(Level.FINEST, "Standalone acks: {0}", acklist.toString()); }
            send(ack);
        }
        lastacks=new Date();
    }

    private boolean firsthandshake=true;

    /** Process a received packet */
    private void processPacket(@Nonnull Packet p) {
        if (Debug.PACKET) { log.log(Level.FINEST, "Received packet: {0}", p.dump()); }
        boolean alreadyseen=acked.containsKey(p.getSequence());
        if (p.getReliable()) {
            // reliable packets must be acked, otherwise the sim will spam us with them a few times
            // we also need to keep track of possible DUPs
            if (Debug.ACK) { log.log(Level.FINEST, "Requested to acknowledge packet {0}", p.getSequence()); }
            synchronized(ackqueue) {
                ackqueue.add(p.getSequence());
            }
            synchronized(acked) {
                acked.put(p.getSequence(),new Date());
            }
        }
        if (p.getResent()) { 
            // is it already known?
            if (alreadyseen) {
                if (Debug.DUMPRETRANS) {
                    log.log(Level.FINER, "Received resent DUPLICATED packet:{0}", p.dump()); 
                } 
                if (Debug.NOTERETRANS) {
                    log.log(Level.FINER, "Received resent DUPLICATED packet:{0}", p.getSequence()); 
                }
                return; // already run it
            }
            if (Debug.DUMPRETRANS) {
                log.log(Level.FINE, "Received resent LOST packet:{0}", p.dump()); 
            }
            if (Debug.NOTERETRANS) {
                log.log(Level.FINE, "Received resent LOST packet:{0}", p.getSequence()); 
            }
        }
        if (alreadyseen & (!p.getResent())) { log.warning("Re-seen packet that is not a retransmit, simulator/network bug?"); return; }
        boolean internal=false; // circuit control packets.  only for stuff that should never be propagated.  acks and pings basically.
        Message m=p.message();
        //System.out.println(m.getClass().getSimpleName());
        if (m instanceof PacketAck) {
            // packetacks are just bunches of acks.  but might be zerocoded and little endian =)
            PacketAck cast=(PacketAck)m;
            for (PacketAck_bPackets ack:cast.bpackets) {
                //public void receivedAck(ack.)
                receivedAck(ack.vid.value);
            }
            internal=true;
        }
        if (m instanceof StartPingCheck) {
            // yes yes, we're here
            CompletePingCheck ping=new CompletePingCheck();
            ping.bpingid.vpingid=((StartPingCheck) m).bpingid.vpingid;
            send(ping);
            internal=true;
        }
        if (m instanceof KickUser) {
            KickUser kick=(KickUser)m;
            internal=true;
            bot.shutdown("Kicked from Second Life: "+kick.buserinfo.vreason.toString());
        }
        if (m instanceof RegionHandshake) {
            // this comes back soon after we establish the connection, and needs to be replied to explicitly (not just acked)
            //internal=true; // we no longer mark this as internal so that it can be used by the Region module
            RegionHandshake r=(RegionHandshake)m;
            regionname=r.bregioninfo.vsimname.toString();
            this.setName("Circuit for "+bot.getUsername()+" to "+this.regionname);
            log=bot.getLogger("Circuit."+regionname);
            Global.regionName(regionhandle, regionname);
            boolean register=false; // most connections register at creation since we know where we're connecting to
            // the initial circuit maybe not so much
            regionuuid=r.bregioninfo2.vregionid;
            if (Debug.REGIONHANDLES) { log.log(Level.FINE, "RegionHandshake with UUID {0}", regionuuid.toUUIDString()); }
            if (firsthandshake) {
                log.log(Level.INFO, "Handshake {0} cpu {1}/{2} active as {3}#{4} @ colocation {5}", new Object[]{regionname, r.bregioninfo3.vcpuclassid.value, r.bregioninfo3.vcpuratio.value, r.bregioninfo3.vproductname.toString(), r.bregioninfo3.vproductsku.toString(), r.bregioninfo3.vcoloname.toString()});
                firsthandshake=false;
            }
            RegionHandshakeReply reply=new RegionHandshakeReply();
            reply.bagentdata.vagentid=bot.getUUID();
            reply.bagentdata.vsessionid=bot.getSession();
            Packet replypacket=new Packet(reply);
            replypacket.setReliable(true);
            replypacket.setZeroCode(true);
            send(replypacket);
            // open our eyes
            bot.setMaxFOV();
            bot.agentUpdate();
        }
        if (m instanceof DisableSimulator) {
            if (!disconnectlogged) { disconnectlogged=true; log.log(Level.INFO, "Circuit closure was requested from Simulator {0} via DisableSimulator", regionname); }
            close();
            internal=true;
        }
        if (!internal) { 
            // if it wasn't a circuit related packet, hand it off to the bot
            if (Debug.REGIONHANDLES && regionhandle==null) { log.info("Creating event with null RH :( "); }
            Regional r;
            r=regional();
            new UDPEvent(bot, r, m, m.getName()).submit();
        }
        
    }
    public long handle() {
        return regionhandle;
    }

    public JSLBot bot() {
        return bot;
    }
    
    @Nullable
    public Regional regional() { return regional; }

    /** Fire up CAPS for this simulator.
     * Avoid replacing existing caps with a duplicate, log if we're replacing a non duplicate...
     * @param newcapsurl The new CAPS url
     */
    public void connectCAPS(String newcapsurl) {
        if (caps!=null && caps.eventqueue()!=null && caps.eventqueue().isAlive()) {
            if (capsurl!=null && capsurl.equals(newcapsurl)) {
                if (Debug.EVENTQUEUE) { log.fine("Passed duplicate of existing CAPS url, not reconnecting anything"); return; }
            }
            if (Debug.EVENTQUEUE) { log.info("Passed DIFFERENT caps URL to existing CAPS url which is still alive?"); return; }
        }
        capsurl=newcapsurl;
        if (Debug.EVENTQUEUE) { log.log(Level.INFO, "Establishing connection to CAPS for region {0}", getRegionName()); }
        caps=new CAPS(this,newcapsurl);
        caps.start();
    }

    @Nonnull
    @Override
    public String toString() { return getRegionName()+"#"+circuitsequence; }

    public Logger getLogger(String subspace) {
        return Logger.getLogger(log.getName()+"."+subspace);
    }

}
