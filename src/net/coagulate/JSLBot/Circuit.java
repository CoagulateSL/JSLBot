package net.coagulate.JSLBot;

import java.io.Closeable;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Messages.AgentFOV;
import net.coagulate.JSLBot.Packets.Messages.AgentMovementComplete;
import net.coagulate.JSLBot.Packets.Messages.CloseCircuit;
import net.coagulate.JSLBot.Packets.Messages.CompletePingCheck;
import net.coagulate.JSLBot.Packets.Messages.DisableSimulator;
import net.coagulate.JSLBot.Packets.Messages.KickUser;
import net.coagulate.JSLBot.Packets.Messages.LogoutRequest;
import net.coagulate.JSLBot.Packets.Messages.PacketAck;
import net.coagulate.JSLBot.Packets.Messages.PacketAck_bPackets;
import net.coagulate.JSLBot.Packets.Messages.RegionHandshake;
import net.coagulate.JSLBot.Packets.Messages.RegionHandshakeReply;
import net.coagulate.JSLBot.Packets.Messages.StartPingCheck;
import net.coagulate.JSLBot.Packets.Packet;
import net.coagulate.JSLBot.Packets.Types.*;

/** Handles a UDP circuit between us and a Simulator.
 * @author Iain Price <git@predestined,net>
 */
public class Circuit extends Thread implements Closeable {
    // 16kb UDP packet.  arbitary number, probably very excessive for SL
    private static final int MAX_BUFFER=16384;
    // list of sent reliable packets we're waiting to hear an ack for
    private final Map<Packet,Date> inflight=new HashMap<Packet,Date>();
    // packets we have already acked and shouldn't reprocess, beyond re-acking
    private final Map<Integer,Date> acked=new HashMap<>();
    // sequence number for packets in this circuit
    private int sequence=1;
    // reference to the agent we're a circuit for
    private JSLBot owner;
    // the simulator's details
    private InetSocketAddress address;
    // our listening socket / endpoint
    private DatagramSocket socket;
    // how long for an unacked packet before retransmit kicks in
    private static final long acktimeout=3000;
    // biggest remote packet we acked or something.  comes up in UDP ping messages, but i dont think we even need it
    private int highestack=0;
    // list of outstanding outbound acks
    private List<Integer> ackqueue=new ArrayList<Integer>();
    // last time we sent any acks, used to force manual PacketAck 
    private Date lastacks=new Date();
    // last housekeeping
    private Date lastmaintenance=new Date();
    // name of the simulator
    private String regionname=""; public String getRegionName() { return regionname; }
    // handle of the simulator
    private LLUUID regionuuid=null;
    // last time we rxed anything
    private Date lackpacket=new Date();
    // the primary all important region handle
    private Long regionhandle=null;
    // primary CAPS url
    private String capsurl=null;
    // primary CAPS object
    private CAPS caps=null;
    // Get the CAPS object attached to this circuit's region
    public CAPS getCAPS() { return caps; }
    // Region object for this circuit's region
    private Regional regional=null;
    // target address
    private String simipandport=null;
    public String getSimIPAndPort() { return simipandport; }
    // how many packets sent
    private int packetrate=0;
    // reduced occasionally, as stored here
    private Date lastreduced=new Date();
    
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
     * @throws IOException 
     */
    Circuit(JSLBot parent, String address, int port, Long passedregionhandle,String capsurl) throws IOException  {
        setDaemon(true);
        simipandport=address+":"+port;
        regional=new Regional(this);
        // stash useful info
        this.capsurl=capsurl;
        owner=parent;
        regionhandle=passedregionhandle;
        if (regionhandle==null) { throw new IllegalArgumentException("Null region handles are not allowed"); }
        this.address=new InetSocketAddress(address,port);
        socket=new DatagramSocket();
        socket.connect(this.address);
        if (Debug.CIRCUIT) { debug("Sending initial circuit code over socket to "+this.address); }
        send(parent.useCircuitCode());
        // launch our background thread, it's purpose is to drive the Circuit, mostly the receive but also acks/retransmits.
        // that thread is not important to keeping the program alive
        this.setDaemon(true);
        this.start(); // launch the rx driver
        try {
            // wait for the 'outstanding acks' to be updated (meaning our reliable UseCircuitCode is acked)
            synchronized(inflight) { inflight.wait(5000); }
        } catch (InterruptedException ex) { throw new IOException("Failed to get UseCircuitCode Ack"); } // no ack in 10 seconds
        // check there are no acks in the queue, should there be, i don't really know what happened, should be one packet out, one ack in, this early on.
        if (Debug.CIRCUIT) { debug("Outstanding ACKS: "+inflight.size()); }
        if (inflight.size()!=0) { throw new IllegalArgumentException("Login completed, UseCircuitCode sent, and not acknowledged..."); }
        info("Successfully connected circuit");
        if (capsurl!=null) { caps=new CAPS(this,capsurl); }
    }
    
    /** Runs the UDP receiver thread */
    public void run() {
        this.setName("Circuit for "+owner.getUsername()+" to "+this.address);
        if (Debug.CIRCUIT) { info("Starting background driver:"+this.getName()); }
        try {
            // create our RX buffer
            DatagramPacket receive=new DatagramPacket(new byte[MAX_BUFFER],MAX_BUFFER);            
            // begin eternal damnation as a circuit driver.
            while (!disconnected) {
                try {
                    socket.setSoTimeout(250);
                    socket.receive(receive);
                    lackpacket = new Date();
                    ByteBuffer rx=ByteBuffer.allocate(receive.getLength());
                    rx.put(receive.getData(),0,receive.getLength());
                    rx.position(0);
                    Packet p=null;
                    try {
                        p=Packet.decode(rx);
                        for (Integer rxack:p.appendedacks) { receivedAck(rxack); }
                    } catch (Exception e) {
                        error("UDP packet decode gave exception "+e.toString(),e);
                        p=null;
                    }
                    if (p==null) {
                        crit("Failed to parse a packet; acks and stuff may be lost");
                    } else {
                        processPacket(p);
                    }
                }
                catch (SocketTimeoutException e) {if (Debug.ACK) { debug("Exiting receive without event"); } } // as requested, and we dont care
                // timeout is just to make sure we get HERE \/ once in a while
                if ((ackqueue.size()>0 && lastAck()>1000) || ackqueue.size()>32) {
                    if (Debug.ACK) { debug("Manually sending ACKs"); }
                    sendAck();                    
                } else {
                    if (Debug.ACK) { debug("NOT sending any ACKs.  Q size is "+ackqueue.size()+" and lastAck is "+lastAck()+"ms"); }
                }
                if (lastMaintenance()>2500) { 
                    maintenance();
                }
            }
        }
        catch (SocketException ex) {
            if (!bot().quitting()) // who cares if we're closing the bot
            { 
                if (!disconnectlogged) { disconnectlogged=true; warn("Circuit to "+this.regionname+" has been closed, "+ex.toString()); }
                close();
            }
        }
        catch (Exception e) {
            if (!disconnectlogged) { disconnectlogged=true; crit("Circuit driver run() loop crashed : "+e.toString(),e); }
            close();
        }
        if (Debug.CIRCUIT) { debug("Deregistering circuit to "+regionname); }
        owner.deregisterCircuit(regionhandle,this);
    }
    /** When the last ack packet was sent, as an interval from now.
     * // TODO this isn't the whole story (since we append to outgoings too)
     * @return 
     */
    private long lastAck() {
        long result=new Date().getTime()-lastacks.getTime();
        return result;
    }
    /** When maintenance last ran, as an interval from now */
    private long lastMaintenance() {
        long result=new Date().getTime()-lastmaintenance.getTime();
        return result;
    }
    /** Add a packet to the list to send ACKs for */
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
                debug("Unexpected ACK "+seqno);
                return;
            }
            inflight.remove(match);
            inflight.notifyAll();
        }
    }
    int maintenancecounter=0;
    /** Run maintenance tasks */
    private void maintenance() throws IOException {
        long interval=new Date().getTime()-lackpacket.getTime();
        if (interval>(Constants.CIRCUIT_PING*1000)) {
            debug("Circuit silent for more than "+Constants.CIRCUIT_PING+" seconds, sending ping.");
            StartPingCheck ping=new StartPingCheck();
            send(ping,true);
        }        
        if (interval>(Constants.CIRCUIT_TIMEOUT*1000)) { disconnectlogged=true; crit("Circuit has received no packets in "+Constants.CIRCUIT_TIMEOUT+" seconds, closing."); close(); return; }
        // polled every 2.5s
        maintenancecounter++;
        lastmaintenance=new Date();
        if ((maintenancecounter % 12) == 0) {
            // trim the list of remember transmissions, if they didn't send after 60 seconds they're dead.
            synchronized(acked) {
                Set<Integer> removeme=new HashSet<>();
                for (int sequence:acked.keySet()) {
                    if (((new Date().getTime())-(acked.get(sequence).getTime()))>60000) {
                        removeme.add(sequence); // can't remove while iterating, concurrent mod exception
                    }
                }
                for (int sequence:removeme) {
                    acked.remove(sequence);
                }
            }
        }
        synchronized (inflight) {
            for (Packet p:inflight.keySet()) {
                // retransmit any packets that haven't been acked in a while
                Date sent=inflight.get(p);
                if (packetrate<5 && ((new Date().getTime())-(sent.getTime()))>acktimeout) {
                    //System.out.println("In retransmit with packetrate "+packetrate);
                    debug("Retransmitting packet "+p.getSequence());
                    p.setResent(true);
                    send(p);
                    inflight.put(p,new Date());
                }
            }
        }
    }
    /** Send a message, optionally reliably */
    public void send(Message m,boolean reliable) throws IOException { Packet p=new Packet(m); p.setReliable(reliable); send(p); }
    /* Send a message, unreliably */
    public void send(Message m) throws IOException { send(m,false); }
    /* Send a packet */
    public void send(Packet p) throws IOException {
        List<Integer> sending=new ArrayList<Integer>(); // list of acks we'll append.
        // synchronise up around the ack queue, strip it , aka "claim it"... 
        synchronized(ackqueue) {
            for (Integer i:ackqueue) {
                sending.add(i); if (i>highestack) { highestack=i; }
            }
            ackqueue.clear();
        }
        
        int acksize=0;
        // acks are 4 bytes each plus a byte 'counter'
        if (sending.size()>0) { acksize=(4*sending.size())+1; p.setAck(true);}
        // claim message+ack size
        ByteBuffer buffer=ByteBuffer.allocate(p.size()+acksize);
        if (buffer.capacity()>1500) { note("Message size is "+buffer.capacity()+" which is quite large"); }
        // write message to the buffer
        p.write(this,buffer);
        byte[] transmit=buffer.array();
        if (p.getZeroCode()) { transmit=BotUtils.zeroEncode(transmit); }
        // final acks aren't zero coded
        String acked="";
        if (buffer.capacity()>1500) { note("Message size is "+sending.size()+" post ZeroCoding which is quite large"); }
        // IF sending acks, append them now (AFTER zerocoding)
        if (sending.size()>0) {
            ByteBuffer append=ByteBuffer.allocate(transmit.length+(4*sending.size())+1);
            append.put(transmit);
            // append acks
            for (Integer i:sending) {
                U32BE acknumber=new U32BE(i); // yup, the appended ones are big endian.   but a PacketAck uses little endian in the body.  LL ;)
                acknumber.write(append);
                acked+=i+" ";
            } // breaks if >256 acks :P
            // finally number of acks, apparently.
            U8 count=new U8(sending.size());
            count.write(append);
            lastacks=new Date();
            if (Debug.ACK) { debug("Appended ACKS:"+acked); }
            transmit=append.array();
        }
        if (Debug.PACKET) { debug("Sending packet: "+p.dump());
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
            try { debug("Reverse engineered: "+Packet.decode(decodeme).dump()); }
            catch (Exception e) { error("REVERSE ENGINEERING EXCEPTION DECODING : "+e.toString(),e); }
        }           
        DatagramPacket packet=new DatagramPacket(transmit,transmit.length,address);
        socket.send(packet);
        packetrate++;
    }

    /** Produce a reliable sequence counter for packets */
    public synchronized int getSequence() {
        return sequence++;
    }

    /** Attempt to close the network socket if this class gets garbage collected somehow */
    protected void finalize() { try { this.close(); super.finalize(); } catch (Throwable e) {}}

    
    private Object lockdisconnecting=new Object();
    /** Close this circuit */
    @Override
    public void close() {
        synchronized(lockdisconnecting) {
            if (disconnecting) { return; }
            disconnecting=true;
        }
        try {
            LogoutRequest l=new LogoutRequest();
            l.bagentdata.vagentid=owner.getUUID();
            l.bagentdata.vsessionid=owner.getSession();
            send(l);
        } catch (Exception e) { }//debug(owner,"Logout Request exceptioned - ",e); }
        try {
            CloseCircuit p=new CloseCircuit();
            send(p);
        } catch (Exception e) { }//debug(owner,"Circuit Close message exceptioned - ",e); }
        try { socket.close(); }
        catch (Exception e) { }//debug(owner,"Socket closure exceptioned - ",e); }
        if (!disconnectlogged) { disconnectlogged=true; debug("We have requested closure of circuit to "+this.regionname); }
        disconnected=true;
    }
    
    /** Send a standalone ACK packet */
    private void sendAck() throws IOException
    {
        //packet ack generator
        List<Integer> sending=new ArrayList<Integer>();
        synchronized(ackqueue) {
            for (Integer i:ackqueue) { sending.add(i); if (i>highestack) { highestack=i; }}
            ackqueue.clear();
        }
        PacketAck ack=new PacketAck();
        ack.bpackets=new ArrayList<>();
        String acked="";
        for (Integer i:sending) {
            PacketAck_bPackets ackblock = new PacketAck_bPackets();
            ackblock.vid=new U32(i);
            ack.bpackets.add(ackblock);
            acked+=i+" ";
        }
        if (Debug.ACK) { debug("Standalone acks: "+acked); }
        send(ack);
        lastacks=new Date();
    }

    private boolean firsthandshake=true;

    /** Process a received packet */
    private void processPacket(Packet p) throws IOException {
        if (Debug.PACKET) { debug("Received packet: "+p.dump()); }
        boolean alreadyseen=acked.containsKey(p.getSequence());
        if (p.getReliable()) {
            // reliable packets must be acked, otherwise the sim will spam us with them a few times
            // we also need to keep track of possible DUPs
            if (Debug.ACK) { debug("Requested to acknowledge packet "+p.getSequence()); }
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
                    debug("Received resent DUPLICATED packet:"+p.dump()); 
                } 
                if (Debug.NOTERETRANS) {
                    debug("Received resent DUPLICATED packet:"+p.getSequence()); 
                }
                return; // already run it
            }
            if (Debug.DUMPRETRANS) {
                debug("Received resent LOST packet:"+p.dump()); 
            }
            if (Debug.NOTERETRANS) {
                debug("Received resent LOST packet:"+p.getSequence()); 
            }
        }
        if (alreadyseen & (!p.getResent())) { warn("Re-seen packet that is not a retransmit, simulator/network bug?"); return; }
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
        if (m instanceof AgentMovementComplete) {
            AgentMovementComplete amc=(AgentMovementComplete) m;
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
            owner.shutdown("Kicked from Second Life: "+kick.buserinfo.vreason.toString());
        }
        if (m instanceof RegionHandshake) {
            // this comes back soon after we establish the connection, and needs to be replied to explicitly (not just acked)
            //internal=true; // we no longer mark this as internal so that it can be used by the Region module
            RegionHandshake r=(RegionHandshake)m;
            regionname=r.bregioninfo.vsimname.toString();
            this.setName("Circuit for "+owner.getUsername()+" to "+this.regionname);
            if (regionhandle!=null) { Global.regionName(regionhandle, regionname); }
            boolean register=false; // most connections register at creation since we know where we're connecting to
            // the initial circuit maybe not so much
            regionuuid=r.bregioninfo2.vregionid;
            if (Debug.REGIONHANDLES) { debug("RegionHandshake with UUID "+regionuuid.toUUIDString()); }
            if (firsthandshake) {
                info("Handshake "+regionname+" cpu "+r.bregioninfo3.vcpuclassid.value+"/"+r.bregioninfo3.vcpuratio.value+" active as "+r.bregioninfo3.vproductname.toString()+"#"+r.bregioninfo3.vproductsku.toString()+" @ colocation "+r.bregioninfo3.vcoloname.toString());
                firsthandshake=false;
            }
            RegionHandshakeReply reply=new RegionHandshakeReply();
            reply.bagentdata.vagentid=owner.getUUID();
            reply.bagentdata.vsessionid=owner.getSession();
            Packet replypacket=new Packet(reply);
            replypacket.setReliable(true);
            replypacket.setZeroCode(true);
            send(replypacket);
            // open our eyes
            AgentFOV fov=new AgentFOV();
            fov.bagentdata.vagentid=owner.getUUID();
            fov.bagentdata.vcircuitcode=new U32(owner.getCC());
            fov.bagentdata.vsessionid=owner.getSession();
            fov.bfovblock.vgencounter=new U32(1);
            fov.bfovblock.vverticalangle=new F32((float)(2*Math.PI));
            send(fov,true);
        }
        if (m instanceof DisableSimulator) {
            if (!disconnectlogged) { disconnectlogged=true; info("Circuit closure was requested from Simulator "+regionname+" via DisableSimulator"); }
            close();
            internal=true;
        }
        if (!internal) { 
            // if it wasn't a circuit related packet, hand it off to the bot
            if (Debug.REGIONHANDLES && regionhandle==null) { System.out.println("Creating event with null RH :( "); }
            Regional r=null;
            if (regionhandle!=null) { r=regional(); }
            new UDPEvent(owner, r, m, m.getName()).submit();
        }
        
    }
    public long handle() {
        return regionhandle;
    }

    public JSLBot bot() {
        return owner;
    }
    
    public Regional regional() { return regional; }

    /** Fire up CAPS for this simulator.
     * Avoid replacing existing caps with a duplicate, log if we're replacing a non duplicate...
     * @param newcapsurl
     * @throws IOException 
     */
    public void connectCAPS(String newcapsurl) throws IOException {
        if (caps!=null && caps.eventqueue().isAlive()) {
            if (capsurl.equals(newcapsurl)) {
                if (Debug.EVENTQUEUE) { debug("Passed duplicate of existing CAPS url, not reconnecting anything"); return; }
            }
            if (Debug.EVENTQUEUE) { debug("Passed DIFFERENT caps URL to existing CAPS url which is still alive?"); return; }
        }
        capsurl=newcapsurl;
        if (Debug.EVENTQUEUE) { debug("Establishing connection to CAPS for region "+getRegionName()); }
        caps=new CAPS(this,newcapsurl);
    }

    void debug(String message) { debug(message,null); }
    void debug(String message, Throwable t) { Log.log(bot(),Log.DEBUG,"("+getRegionName()+") "+message,t); }
    void info(String message) { info(message,null); }
    void info(String message, Throwable t) { Log.log(bot(),Log.INFO,"("+getRegionName()+") "+message,t); }
    void note(String message) { note(message,null); }
    void note(String message, Throwable t) { Log.log(bot(),Log.NOTE,"("+getRegionName()+") "+message,t); }
    void warn(String message) { warn(message,null); }
    void warn(String message, Throwable t) { Log.log(bot(),Log.WARNING,"("+getRegionName()+") "+message,t); }
    void error(String message) { error(message,null); }
    void error(String message, Throwable t) { Log.log(bot(),Log.ERROR,"("+getRegionName()+") "+message,t); }
    void crit(String message) { crit(message,null); }
    void crit(String message, Throwable t) { Log.log(bot(),Log.CRITICAL,"("+getRegionName()+") "+message,t); }    

}
