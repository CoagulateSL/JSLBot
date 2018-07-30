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
import net.coagulate.JSLBot.Packets.Messages.AgentMovementComplete;
import net.coagulate.JSLBot.Packets.Messages.CloseCircuit;
import net.coagulate.JSLBot.Packets.Messages.CompletePingCheck;
import net.coagulate.JSLBot.Packets.Messages.DisableSimulator;
import net.coagulate.JSLBot.Packets.Messages.LogoutRequest;
import net.coagulate.JSLBot.Packets.Messages.PacketAck;
import net.coagulate.JSLBot.Packets.Messages.PacketAck_bPackets;
import net.coagulate.JSLBot.Packets.Messages.RegionHandshake;
import net.coagulate.JSLBot.Packets.Messages.RegionHandshakeReply;
import net.coagulate.JSLBot.Packets.Messages.StartPingCheck;
import net.coagulate.JSLBot.Packets.Messages.UseCircuitCode;
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
    private static final int acktimeout=3;
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
    Long regionhandle=null; // there are different forms of this.  the one i get back from teleportfinish is weird.  we go with agentmovemntcomplete or the mapgridlookup, this seems consistent
    private String capsurl=null;
    private CAPS caps=null;
    public CAPS getCAPS() { return caps; }
    Regional regional=null;
    private String simipandport=null;
    public String getSimIPAndPort() { return simipandport; }
    private int packetrate=0;
    Date lastreduced=new Date();
    // create a circuit given the parameters.  sessionid comes from login, and circuitcode comes from login or TeleportFinished style events that open new circuits
    Circuit(JSLBot parent, String address, int port, Long passedregionhandle,String capsurl) throws IOException  {
        setDaemon(true);
        simipandport=address+":"+port;
        regional=new Regional(this);
        // stash useful info
        this.capsurl=capsurl;
        owner=parent;
        if (passedregionhandle!=null) { regionhandle=passedregionhandle; }
        // firstly check if we're allowed to set up this circuit or if its a duplicate
        this.address=new InetSocketAddress(address,port);
        // create socket on whatever port
        socket=new DatagramSocket();
        // not really sure what this means on a UDP socket, probably nothing :P
        socket.connect(this.address);
        // create UseCircuitCode packet necessary to authenticate / identify our connection to the simulator
        if (Debug.CIRCUIT) { debug("Sending initial circuit code over socket to "+this.address); }
        send(parent.useCircuitCode());
        // launch our background thread, it's purpose is to drive the Circuit, mostly the receive but also acks/retransmits.
        // that thread is not important to keeping the program alive
        this.setDaemon(true);
        this.start(); // launch the rx driver
        try {
            // wait for the 'outstanding acks' to be updated (meaning our reliable UseCircuitCode is acked)
            synchronized(inflight) {
                inflight.wait(5000);
            }
        } catch (InterruptedException ex) { throw new IOException("Failed to get UseCircuitCode Ack"); } // no ack in 10 seconds
        // check there are no acks in the queue, should there be, i don't really know what happened, should be one packet out, one ack in, this early on.
        if (Debug.CIRCUIT) { debug("Outstanding ACKS: "+inflight.size()); }
        if (inflight.size()!=0) { 
            throw new IllegalArgumentException("Login completed, UseCircuitCode sent, and not acknowledged... Unexpected twist in protocol. Aborting.  (or, the ack processor was unable to lock the ack queue to update, logic error with locking)");
        }
        info("Successfully connected circuit");
        if (capsurl!=null) { caps=new CAPS(this,capsurl,regionhandle); }
    }
    
    long maxinterval=0;
    // run when the circuit is established, this performs the blocking IO listening for packets, and does some other circuit maintenance tasks
    public void run() {
        // TO DO
        // clean up the code generally
        // ACKS - we do not check we get acks for our Reliables.  Implement retransmissions and timeouts with WARNs.
        // explain ourselves
        this.setName("Circuit for "+owner.getUsername()+" to "+this.address);
        if (Debug.CIRCUIT) { info("Starting background driver:"+this.getName()); }
        try {
            // create our RX buffer
            DatagramPacket receive=new DatagramPacket(new byte[MAX_BUFFER],MAX_BUFFER);            
            // begin eternal damnation as a circuit driver.
            while (!closing) {
                try {
                    // this causes blocking receives to abort after sometime with no packets.
                    // It has no side effects other than guaranteeing exiting a blocked state (well, and throwing an exception!)
                    socket.setSoTimeout(250);
                    if (Debug.ACK) { debug("Entering blocking receive"); }
                    socket.receive(receive);
                    if (Debug.ACK) { debug("Data RX exit blocking receive"); }
                    //long interval=new Date().getTime()-lackpacket.getTime();
                    //if (interval>maxinterval) { maxinterval=interval; System.out.println("Largest inter-rx interval is now "+maxinterval); }
                    lackpacket = new Date();
                    ByteBuffer rx=ByteBuffer.allocate(receive.getLength());
                    rx.put(receive.getData(),0,receive.getLength());
                    rx.position(0);
                    Packet p=null;
                    try {
                        p=Packet.decode(rx);
                        for (Integer rxack:p.appendedacks) {
                            //System.out.println("RXACK "+rxack);
                            receivedAck(rxack);
                        }
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
            if (!bot().quit) { warn("Circuit to "+this.regionname+" has been closed, "+ex.toString()); }
        }
        catch (Exception e) {
            crit("Circuit driver run() loop crashed : "+e.toString(),e);
        }
        if (Debug.CIRCUIT) { debug("Deregistering circuit to "+regionname); }
        owner.deregisterCircuit(regionhandle,this);
    }
    public long lastAck() {
        // when we last sent acks - if this is too long ago we must forge and send a PacketAck rather than waiting for append opportunities
        Date now=new Date();
        long result=now.getTime()-lastacks.getTime();
        return result;
    }
    public long lastMaintenance() {
        // when we last sent acks - if this is too long ago we must forge and send a PacketAck rather than waiting for append opportunities
        Date now=new Date();
        long result=now.getTime()-lastmaintenance.getTime();
        return result;
    }
    public void requiresAck(Packet p) {
        synchronized(inflight) {
            inflight.put(p,new Date());
        }
    }
    public void receivedAck(int seqno)
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
    void maintenance() throws IOException {
        long interval=new Date().getTime()-lackpacket.getTime();
        if (interval>(JSLBot.CIRCUIT_TIMEOUT*1000)) { crit("Circuit has received no packets in "+JSLBot.CIRCUIT_TIMEOUT+" seconds, closing."); close(); return; }
        // polled every 10s
        maintenancecounter++;
        lastmaintenance=new Date();
        if ((maintenancecounter % 10) == 0) {
            // trim remembered packets.
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
                Date sent=inflight.get(p);
                if (packetrate<5 && ((new Date().getTime())-(sent.getTime()))>5000) {
                    System.out.println("In retransmit with packetrate "+packetrate);
                    debug("Retransmitting packet "+p.getSequence());
                    p.setResent(true);
                    send(p);
                    inflight.put(p,new Date());
                }
            }
        }
    }
    public void send(Message m,boolean reliable) throws IOException { Packet p=new Packet(m); p.setReliable(reliable); send(p); }
    public void send(Message m) throws IOException { send(m,false); }
    public void send(Packet p) throws IOException {
        // THIS CODE IS BAD
        // it works, but its ugly.
        // the whole ordering of zerocoding, ack appending and so on needs to be cleaned up and done better
        List<Integer> sending=new ArrayList<Integer>();
        
        // synchronise up around the ack queue, strip it , aka "claim it"
        synchronized(ackqueue) {
            for (Integer i:ackqueue) { sending.add(i); if (i>highestack) { highestack=i; }}
            ackqueue.clear();
        }
        
        int acksize=0;
        // acks are 4 bytes each plus a byte 'counter'
        if (sending.size()>0) { acksize=(4*sending.size())+1; p.setAck(true);}
        // claim message+ack size
        ByteBuffer buffer=ByteBuffer.allocate(p.size()+acksize);
        if (buffer.capacity()>1500) { note("Message size is "+buffer.capacity()+" which is quite large"); }
        // write message
        p.write(this,buffer);
        // debug
        byte[] transmit;
        if (p.getZeroCode()) {
            // if we're zerocoding, we just unmarshall into an array and step over it coping it into a byte list
            byte[] input=buffer.array();
            List<Byte> output=new ArrayList<>();
            // first 5 bytes (header) are not encoded
            for (int i=0;i<6;i++) { output.add(input[i]); }
            int zerocount=0;
            // rest is
            for (int i=6;i<input.length;i++) {
                if (input[i]==0) {
                    zerocount++;
                } else {
                    if (zerocount>0) {
                        output.add((byte)0);
                        output.add((byte)zerocount);
                        zerocount=0;
                    }
                    output.add(input[i]);
                }
            }
            if (zerocount>0) {
                output.add((byte)0);
                output.add((byte)zerocount);
                zerocount=0;
            }
            transmit=new byte[output.size()];
            int offset=0;
            // and put it back into the byte array :P
            for (Byte b:output) {
                transmit[offset]=b; offset++;
            }
        } else { transmit=buffer.array(); }
        // final acks aren't zero coded
        String acked="";
        if (buffer.capacity()>1500) { note("Message size is "+sending.size()+" post ZeroCoding which is quite large"); }
        if (sending.size()>0) {
            // but since we packed for transmission, we unpack, expand, blah blah, not really very elegant, zerocoding should probably be a factor around the messageWrite call :/
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
        if (false || Debug.PACKET) {
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

    public synchronized int getSequence() {
        return sequence++;
    }

    protected void finalize() { try { this.close(); super.finalize(); } catch (Throwable e) {}}

    @Override
    public void close() {
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
        if (!closing) { debug("We have requested closure of circuit to "+this.regionname); }
        closing=true;
    }
    
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
                return; // alrea dy run it
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
        if (m instanceof RegionHandshake) {
            // this comes back soon after we establish the connection, and needs to be replied to explicitly (not just acked)
            //internal=true; // we no longer mark this as internal so that it can be used by the Region module
            RegionHandshake r=(RegionHandshake)m;
            regionname=r.bregioninfo.vsimname.toString();
            this.setName("Circuit for "+owner.getUsername()+" to "+this.regionname);
            if (regionhandle!=null) { Global.setRegionName(regionhandle, regionname); }
            boolean register=false; // most connections register at creation since we know where we're connecting to
            // the initial circuit maybe not so much
            regionuuid=r.bregioninfo2.vregionid;
            if (Debug.REGIONHANDLES) { debug("RegionHandshake with UUID "+regionuuid.toUUIDString()); }
            info("Handshake "+regionname+" cpu "+r.bregioninfo3.vcpuclassid.value+"/"+r.bregioninfo3.vcpuratio.value+" active as "+r.bregioninfo3.vproductname.toString()+"#"+r.bregioninfo3.vproductsku.toString()+" @ colocation "+r.bregioninfo3.vcoloname.toString());
            RegionHandshakeReply reply=new RegionHandshakeReply();
            reply.bagentdata.vagentid=owner.getUUID();
            reply.bagentdata.vsessionid=owner.getSession();
            Packet replypacket=new Packet(reply);
            replypacket.setReliable(true);
            replypacket.setZeroCode(true);
            send(replypacket);
        }
        if (m instanceof DisableSimulator) {
            if (!closing) { info("Circuit closure was requested from Simulator "+regionname+" via DisableSimulator"); }
            closing=true;
            close();
            owner.dropCircuit(this);
            internal=true;
        }
        if (!internal) { 
            // if it wasn't a circuit related packet, hand it off to the bot
            if (Debug.REGIONHANDLES && regionhandle==null) { System.out.println("Creating event with null RH :( "); }
            Regional r=null;
            if (regionhandle!=null) { r=getRegional(); }
            owner.process(new Event(p,r));
        }
        
    }
    private boolean closing=false;

    long getHandle() {
        return regionhandle;
    }

    public JSLBot bot() {
        return owner;
    }
    
    public Regional getRegional() { return regional; }

    public void connectCAPS(String newcapsurl) throws IOException {
        if (caps!=null && caps.isAlive()) {
            if (capsurl.equals(newcapsurl)) {
                if (Debug.EVENTQUEUE) { debug("Passed duplicate of existing CAPS url, not reconnecting anything"); return; }
            }
            if (Debug.EVENTQUEUE) { debug("Passed DIFFERENT caps URL to existing CAPS url which is still alive?"); return; }
        }
        capsurl=newcapsurl;
        if (Debug.EVENTQUEUE) { debug("Establishing connection to CAPS for region "+getRegionName()); }
        caps=new CAPS(this,newcapsurl,regionhandle);
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
