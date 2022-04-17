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

/**
 * Handles a UDP circuit between us and a Simulator.
 *
 * @author Iain Price
 */
public final class Circuit extends Thread implements Closeable {
	private final AtomicInteger bytesin=new AtomicInteger(0);
	private final AtomicInteger bytesout=new AtomicInteger(0);
	// list of sent reliable packets we're waiting to hear an ack for
	private final Map<Packet,Date> inflight=new HashMap<>();
	// packets we have already acked and shouldn't reprocess, beyond re-acking
	private final Map<Integer,Date> acked=new HashMap<>();
	// list of outstanding outbound acks
	private final List<Integer> ackqueue=new ArrayList<>();
	private final Object lockdisconnecting=new Object();
	private final int circuitsequence;
	// reference to the agent we're a circuit for
	@Nullable
	private JSLBot bot;
	// the primary all important region handle
	@Nonnull
	private final Long regionhandle;
	// Region object for this circuit's region
	@Nullable
	private final Regional regional;
	// target address
	@Nullable
	private final String simip;
	private final int simport;
	int maintenancecounter;
	private Logger log;
	// sequence number for packets in this circuit
	private int sequence=1;
	// the simulator's details
	@Nullable
	private InetSocketAddress address;
	// our listening socket / endpoint
	private DatagramSocket socket;
	// biggest remote packet we acked or something.  comes up in UDP ping messages, but i dont think we even need it
	private int highestack;
	// last time we sent any acks, used to force manual PacketAck
	@Nonnull
	private Date lastacks=new Date();
	// last housekeeping
	@Nonnull
	private Date lastmaintenance=new Date();
	// name of the simulator
	@Nonnull
	private String regionname="";
    // last time we rxed anything
	@Nonnull
	private Date lastpacket=new Date();
	// primary CAPS url
	@Nullable
	private String capsurl;
	// primary CAPS object
	@Nullable
	private CAPS caps;
	// how many packets sent
	private int packetrate;
	// Have started a disconnect, so dont error so much
	private boolean disconnecting;
	// Have given a reason for the disconnect, so dont give any more
	private boolean disconnectlogged;

	// clean this up
	// We're dead.
	private boolean disconnected;
	private boolean hasrunconnect;
	private boolean pinged;
	private boolean firsthandshake=true;

	/**
	 * create a circuit given the parameters.
	 *
	 * @param parent             Owning JSLBot
	 * @param address            Target IP address
	 * @param port               Target port
	 * @param passedregionhandle Target region handle
	 * @param capsurl            Target CAPS url
	 */
	Circuit(@Nonnull final JSLBot parent,
	        @Nullable final String address,
	        final int port,
	        @Nullable final Long passedregionhandle,
	        @Nullable final String capsurl) {
		log=parent.getLogger("Circuit."+address+":"+port);
		if (passedregionhandle==null) { throw new IllegalArgumentException("Null region handles are not allowed"); }
		circuitsequence=parent.getCircuitSequence();
		setDaemon(true);
		simip=address;
		simport=port;
		regional=new Regional(this);
		this.capsurl=capsurl;
		bot=parent;
		regionhandle=passedregionhandle;
	}

	// ---------- INSTANCE ----------
	@Nonnull
	public String getRegionName() { return regionname; }

	// Get the CAPS object attached to this circuit's region
	@Nonnull
	public CAPS getCAPS() {
		if (caps==null) { throw new IllegalStateException("CAPS is not currently configured for this circuit"); }
		return caps;
	}
	@Nullable
	public CAPS getCAPSNullable() {
		return caps;
	}

	@Nonnull
	public String getSimIPAndPort() { return simip+":"+simport; }

	/**
	 * Connect to the sim.
	 * This will send initial packets and wait for them to be acknowledged.
	 * Automatically starts the main circuit thread to receive said acknowledges.
	 * Use me instead of run()
	 * Note:  This function may take some time to return.
	 *
	 * @throws IOException Failure to connect
	 */
	public void connect() throws IOException {
		if (simip==null || simport==0) {
			throw new IllegalStateException("Sim IP + port are not initialised when connect()ing");
		}
		hasrunconnect=true;
		address=new InetSocketAddress(simip,simport);
		socket=new DatagramSocket();
		socket.connect(address);
		if (Debug.CIRCUIT) { log.finer("Sending initial circuit code over socket to "+address); }
		send(bot().useCircuitCode());
		setDaemon(true);
		start(); // launch the rx driver
		// wait for the 'outstanding acks' to be updated (meaning our reliable UseCircuitCode is acked)
		try { synchronized (inflight) { inflight.wait(5000); } }
		catch (@Nonnull final InterruptedException ex) {
			throw new IOException("Failed to get UseCircuitCode Ack");
		}
		if (Debug.CIRCUIT) { log.finer("Outstanding ACKS: "+inflight.size()); }
		if (!inflight.isEmpty()) {
			throw new IOException("Login completed, UseCircuitCode sent, and not acknowledged...");
		}
		log.info("Successfully connected circuit");
		if (capsurl!=null) {
			caps=new CAPS(this,capsurl);
			//noinspection CallToThreadRun
			caps.run();
		}
	}

	/**
	 * Runs the UDP receiver thread.
	 * Do not call, run connect().
	 */
	@Override
	public void run() {
		setName("Circuit for "+bot().getUsername()+" to "+address);
		if (!hasrunconnect) {
			log.severe("Must connect a new circuit");
			return;
		}
		if (Debug.CIRCUIT) { log.fine("Starting background driver:"+getName()); }
		try {
			@Nonnull final DatagramPacket receive=new DatagramPacket(new byte[Constants.UDP_MAX_BUFFER],Constants.UDP_MAX_BUFFER);
			// begin eternal damnation as a circuit driver.
			while (!disconnected && !disconnectlogged && !bot().quitting()) {
				try {
					socket.setSoTimeout(250);
					socket.receive(receive);
					// if we received nothing, go to "Socket Timeout Exception"
					lastpacket=new Date();
					@Nonnull final ByteBuffer rx=ByteBuffer.allocate(receive.getLength());
					rx.put(receive.getData(),0,receive.getLength());
					rx.position(0);
					if (Constants.PACKET_ACCOUNTING) {
						bytesin.getAndAdd(receive.getLength());
						bot.bytesin.getAndAdd(receive.getLength());
					}
					@Nonnull final Packet p;
					p=Packet.decode(rx);
					if (p!=null) {
						if (Constants.PACKET_ACCOUNTING_BY_MESSAGE) {
							@Nullable JSLBot botcopy = bot;
							if (botcopy != null && p != null) {
								botcopy.accountMessageIn(p.message().getId(), receive.getLength());
							}
						}
						for (final Integer rxack : p.appendedacks) {
							receivedAck(rxack);
						}
						//if (p.getReliable()) { System.out.println("ACK REQUIRED IN:"+p.getName()); }
						//System.out.println("RX: "+p.getName());
						processPacket(p);
					}
				}
				catch (@Nonnull final SocketTimeoutException e) {
					if (Debug.ACK) { log.finer("Exiting receive without event"); }
				} // as requested, and we dont care
				// timeout is just to make sure we get HERE \/ once in a while
				if ((ackqueue.size()>0 && lastAck()>2000) || ackqueue.size()>32) {
					if (Debug.ACK) { log.finer("Manually sending ACKs"); }
					sendAck();
				}
				else {
					if (Debug.ACK) {
						log.finer("NOT sending any ACKs.  Q size is "+ackqueue.size()+" and lastAck is "+lastAck()+"ms");
					}
				}
				if (lastMaintenance()>2500) {
					maintenance();
				}
			}
		}
		catch (@Nonnull final SocketException ex) {
			if (!bot().quitting()) // who cares if we're closing the bot
			{
				if (!disconnectlogged) {
					disconnectlogged=true;
					log.warning("Circuit to "+regionname+" has been closed, "+ex);
				}
				close();
			}
		}
		catch (@Nonnull final RuntimeException|IOException e) {
			if (!disconnectlogged) {
				disconnectlogged=true;
				log.log(SEVERE,"Circuit driver run() loop crashed : "+e,e);
			}
			close();
		}
		if (Debug.CIRCUIT) { log.log(Level.FINE,"Deregistering circuit to {0}",regionname); }
		@Nullable JSLBot mybot=bot;
		if (mybot!=null) { mybot.deregisterCircuit(regionhandle,this); }
	}

	@Nonnull
	@Override
	public String toString() { return getRegionName()+"#"+circuitsequence; }

	/**
	 * Add a packet to the list to send ACKs for
	 *
	 * @param p Packet to require ACK for
	 */
	public void requiresAck(final Packet p) {
		synchronized (inflight) {
			inflight.put(p,new Date());
		}
	}

	/**
	 * Send a message, optionally reliably
	 *
	 * @param m        Message to send
	 * @param reliable Send as reliable/require ACKs
	 */
	public void send(final Message m,
	                 final boolean reliable) throws IllegalStateException {
		@Nonnull final Packet p=new Packet(m);
		p.setReliable(reliable);
		try { send(p); }
		catch (IOException e) { this.close(); throw new IllegalStateException("Errors closed the circuit",e); }
	}

	/* Send a message, unreliably */
	public void send(final Message m) { send(m,false); }

	/* Send a packet */
	public void send(@Nonnull final Packet p) throws IOException {
		@Nonnull final List<Integer> sending=new ArrayList<>(); // list of acks we'll append.
		// synchronise up around the ack queue, strip it , aka "claim it"...
		synchronized (ackqueue) {
			for (final Integer i: ackqueue) {
				sending.add(i);
				if (i>highestack) { highestack=i; }
			}
			ackqueue.clear();
			lastacks=new Date();
		}

		int acksize=0;
		// acks are 4 bytes each plus a byte 'counter'
		if (sending.size()>0) {
			acksize=(4*sending.size())+1;
			p.setAck(true);
		}
		// claim message+ack size
		@Nonnull final ByteBuffer buffer=ByteBuffer.allocate(p.size()+acksize);
		if (buffer.capacity()>1500) {
			log.log(Level.WARNING,"Message size is {0} which is quite large",buffer.capacity());
		}
		// write message to the buffer
		p.write(this,buffer);
		@Nonnull byte[] transmit=buffer.array();
		if (p.getZeroCode()) { transmit=BotUtils.zeroEncode(transmit); }
		// final acks aren't zero coded
		@Nonnull final StringBuilder ackedlist=new StringBuilder();
		if (buffer.capacity()>1500) {
			log.log(Level.WARNING,"Message size is {0} post ZeroCoding which is quite large",sending.size());
		}
		// IF sending acks, append them now (AFTER zerocoding)
		if (sending.size()>0) {
			@Nonnull final ByteBuffer append=ByteBuffer.allocate(transmit.length+(4*sending.size())+1);
			append.put(transmit);
			// append acks
			for (final Integer i: sending) {
				@Nonnull final U32BE acknumber=new U32BE(i); // yup, the appended ones are big endian.   but a PacketAck uses little endian in the body.  LL ;)
				acknumber.write(append);
				ackedlist.append(i).append(" ");
			} // breaks if >256 acks :P
			// finally number of acks, apparently.
			@Nonnull final U8 count=new U8(sending.size());
			count.write(append);
			lastacks=new Date();
			if (Debug.ACK) { log.log(Level.FINEST,"Appended ACKS:{0}",ackedlist.toString()); }
			transmit=append.array();
		}
		if (Debug.PACKET) {
			log.log(Level.FINEST,"Sending packet: {0}",p.dump());
			System.out.println();
			for (final Byte b: transmit) {
				System.out.print(b+" ");
			}
			System.out.println();
		}
		if (Debug.PACKET) {
			// nice debugger, pretend we received our own packet, do we dismantle it properly?
			@Nonnull final ByteBuffer decodeme=ByteBuffer.allocate(transmit.length);
			decodeme.put(transmit);
			decodeme.position(0);
			log.log(Level.FINEST,"Reverse engineered: {0}",Packet.decode(decodeme).dump());
		}
		@Nonnull final DatagramPacket packet=new DatagramPacket(transmit,transmit.length,address());
        /*if (p.getName().equals("AgentUpdate")) {
            Exception e=new Exception("Generate stack trace");
            e.printStackTrace(); // show me!
        }*/
		//System.out.println("TX: "+p.getName());
		@Nullable JSLBot botcopy = bot;
		if (Constants.PACKET_ACCOUNTING) {
			bytesout.addAndGet(packet.getLength());
			if (botcopy!=null) { botcopy.bytesout.addAndGet(packet.getLength()); }
		}
		if (Constants.PACKET_ACCOUNTING_BY_MESSAGE) {
			if (botcopy!=null) { botcopy.accountMessageOut(p.getId(),packet.getLength()); }
		}
		try { socket.send(packet); errors=0; }
		catch (@Nonnull final IOException e) {
			log.log(SEVERE,"Error transmitting packet "+e,e);
			errors++;
			if (errors>=3) { throw new IOException("Circuit to "+getRegionName()+" failed 3x",e); }
		}
		packetrate++;
	}
	private int errors=0;

	/**
	 * Produce a reliable sequence counter for packets
	 *
	 * @return Atomicly incrementing sequence number
	 */
	public synchronized int getSequence() {
		return sequence++;
	}

	/**
	 * Close this circuit
	 */
	@Override
	public void close() {
		synchronized (lockdisconnecting) {
			if (disconnecting) { return; }
			disconnecting=true;
		}
		@Nullable CAPS ourcaps=getCAPSNullable();
		if (ourcaps!=null) {
			@Nullable EventQueue oureventqueue=ourcaps.eventqueue();
			if (oureventqueue!=null) {
				oureventqueue.shutdown();
			}
		}
		@Nonnull final LogoutRequest l=new LogoutRequest(); //debug(owner,"Logout Request exceptioned - ",e); }
		l.bagentdata.vagentid=bot.getUUID();
		l.bagentdata.vsessionid=bot.getSession();
		send(l);
		@Nonnull final CloseCircuit p=new CloseCircuit(); //debug(owner,"Circuit Close message exceptioned - ",e); }
		send(p);
		try {
			socket.close();
		}
		catch (@Nonnull final Exception ignored) { }//debug(owner,"Socket closure exceptioned - ",e); }
		if (!disconnectlogged) {
			disconnectlogged=true;
			log.log(Level.FINE,"We have requested closure of circuit to {0}",regionname);
		}
		disconnected=true;
		// disconnect us from the bot so nothing can reach back up, or at least it will NPE when it tries (debugging reconnection)
		bot=null;
	}

	public long handle() {
		return regionhandle;
	}

	@Nonnull
	public JSLBot bot() {
		@Nullable JSLBot mybot=bot;
		if (mybot==null) { throw new NullPointerException("Bot connection has gone away"); }
		return bot;
	}
	@Nullable
	public JSLBot botNullable() {
		return bot;
	}

	@Nonnull
	public Regional regional() {
		if (regional==null) {
			throw new NullPointerException("Regional information not established for this circuit yet");
		}
		return regional;
	}

	/**
	 * Fire up CAPS for this simulator.
	 * Avoid replacing existing caps with a duplicate, log if we're replacing a non duplicate...
	 *
	 * @param newcapsurl The new CAPS url
	 */
	public void connectCAPS(final String newcapsurl) {
		if (caps!=null && caps.eventqueue()!=null && caps.eventqueue().isAlive()) {
			if (capsurl!=null && capsurl.equals(newcapsurl)) {
				if (Debug.EVENTQUEUE) {
					log.fine("Passed duplicate of existing CAPS url, not reconnecting anything");
					return;
				}
			}
			if (Debug.EVENTQUEUE) {
				log.info("Passed DIFFERENT caps URL to existing CAPS url which is still alive?");
				return;
			}
		}
		capsurl=newcapsurl;
		if (Debug.EVENTQUEUE) { log.log(Level.INFO,"Establishing connection to CAPS for region {0}",getRegionName()); }
		caps=new CAPS(this,newcapsurl);
		caps.start();
	}

	public Logger getLogger(final String subspace) {
		return Logger.getLogger(log.getName()+"."+subspace);
	}

	// ----- Internal Instance -----
	@Nonnull
	private InetSocketAddress address() {
		if (address==null) {
			throw new IllegalStateException("Attempting to get circuit socket address but it's null, weirdly");
		}
		return address;
	}

	/**
	 * When the last ack packet was sent, as an interval from now.
	 *
	 * @return How long ago last acks were sent, in milliseconds
	 */
	private long lastAck() {
		return new Date().getTime()-lastacks.getTime();
	}

	/**
	 * When maintenance last ran, as an interval from now
	 *
	 * @return How long ago maintenance last ran, in milliseconds
	 */
	private long lastMaintenance() {
		return new Date().getTime()-lastmaintenance.getTime();
	}

	/**
	 * Handle a received ACK
	 */
	private void receivedAck(final int seqno) {
		@Nullable Packet match=null;
		synchronized (inflight) {
			for (@Nonnull final Packet p: inflight.keySet()) {
				if (p.getSequence()==seqno) { match=p; }
			}
			if (match==null) {
				log.log(Level.FINE,"Unexpected ACK {0}",seqno);
				return;
			}
			inflight.remove(match);
			inflight.notifyAll();
		}
	}

	/**
	 * Run maintenance tasks
	 */
	private void maintenance() {
		final long interval=new Date().getTime()-lastpacket.getTime();
		if (interval>(Constants.CIRCUIT_PING*1000)) {
			if (!pinged) {
				pinged=true;
				log.log(Level.FINE,"Circuit silent for more than {0} seconds, sending ping.",Constants.CIRCUIT_PING);
				@Nonnull final StartPingCheck ping=new StartPingCheck();
				send(ping,true);
			}
		}
		else { pinged=false; } // ping when crossing threshold.  once.  reset threshold detection here.
		if (interval>(Constants.CIRCUIT_TIMEOUT*1000)) {
			disconnectlogged=true;
			log.log(SEVERE,"Circuit has received no packets in {0} seconds, closing.",Constants.CIRCUIT_TIMEOUT);
			close();
			return;
		}
		// polled every 2.5s
		maintenancecounter++;
		lastmaintenance=new Date();
		if ((maintenancecounter%12)==0) {
			// trim the list of remember transmissions, if they didn't send after 60 seconds they're dead.
			synchronized (acked) {
				@Nonnull final Set<Integer> removeme=new HashSet<>();
				for (@Nonnull final Map.Entry<Integer,Date> entry: acked.entrySet()) {
					if (((new Date().getTime())-(entry.getValue().getTime()))>60000) {
						removeme.add(entry.getKey()); // can't remove while iterating, concurrent mod exception
					}
				}
				for (final int seq: removeme) {
					acked.remove(seq);
				}
			}
		}
		synchronized (inflight) {
			for (@Nonnull final Map.Entry<Packet,Date> entry: inflight.entrySet()) {
				final Packet p=entry.getKey();
				// retransmit any packets that haven't been acked in a while
				final Date sent=entry.getValue();
				if (packetrate<5 && ((new Date().getTime())-(sent.getTime()))>Constants.ACK_TIMEOUT) {
					//System.out.println("In retransmit with packetrate "+packetrate);
					log.finer("Retransmitting packet "+p.getSequence());
					p.setResent(true);
					try {
						send(p);
						inflight.put(p, new Date());
					} catch (IOException e) { this.close(); throw new IllegalStateException("Failed to send a retransmit",e); }
				}
			}
		}
	}

	/**
	 * Send a standalone ACK packet
	 */
	private void sendAck() {
		//packet ack generator
		@Nonnull final List<Integer> sending=new ArrayList<>();
		synchronized (ackqueue) {
			for (final Integer i: ackqueue) {
				sending.add(i);
				if (i>highestack) { highestack=i; }
			}
			ackqueue.clear();
		}
		if (!sending.isEmpty()) {
			@Nonnull final PacketAck ack=new PacketAck();
			ack.bpackets=new ArrayList<>();
			@Nonnull final StringBuilder acklist=new StringBuilder();
			for (final Integer i: sending) {
				@Nonnull final PacketAck_bPackets ackblock=new PacketAck_bPackets();
				ackblock.vid=new U32(i);
				ack.bpackets.add(ackblock);
				acklist.append(i).append(" ");
			}
			if (Debug.ACK) { log.log(Level.FINEST,"Standalone acks: {0}",acklist.toString()); }
			send(ack);
		}
		lastacks=new Date();
	}

	/**
	 * Process a received packet
	 */
	private void processPacket(@Nonnull final Packet p) {
		if (Debug.PACKET) { log.log(Level.FINEST,"Received packet: {0}",p.dump()); }
		final boolean alreadyseen=acked.containsKey(p.getSequence());
		if (p.getReliable()) {
			// reliable packets must be acked, otherwise the sim will spam us with them a few times
			// we also need to keep track of possible DUPs
			if (Debug.ACK) { log.log(Level.FINEST,"Requested to acknowledge packet {0}",p.getSequence()); }
			synchronized (ackqueue) {
				ackqueue.add(p.getSequence());
			}
			synchronized (acked) {
				acked.put(p.getSequence(),new Date());
			}
		}
		if (p.getResent()) {
			// is it already known?
			if (alreadyseen) {
				if (Debug.DUMPRETRANS) {
					log.log(Level.FINER,"Received resent DUPLICATED packet:{0}",p.dump());
				}
				if (Debug.NOTERETRANS) {
					log.log(Level.FINER,"Received resent DUPLICATED packet:{0}",p.getSequence());
				}
				return; // already run it
			}
			if (Debug.DUMPRETRANS) {
				log.log(Level.FINE,"Received resent LOST packet:{0}",p.dump());
			}
			if (Debug.NOTERETRANS) {
				log.log(Level.FINE,"Received resent LOST packet:{0}",p.getSequence());
			}
		}
		if (alreadyseen&(!p.getResent())) {
			log.warning("Re-seen packet that is not a retransmit, simulator/network bug?");
			return;
		}
		boolean internal=false; // circuit control packets.  only for stuff that should never be propagated.  acks and pings basically.
		@Nullable final Message m=p.messageNullable();
		//System.out.println(m.getClass().getSimpleName());
		if (m instanceof PacketAck) {
			// packetacks are just bunches of acks.  but might be zerocoded and little endian =)
			@Nullable final PacketAck cast=(PacketAck) m;
			for (@Nonnull final PacketAck_bPackets ack: cast.bpackets) {
				//public void receivedAck(ack.)
				receivedAck(ack.vid.value);
			}
			internal=true;
		}
		if (m instanceof StartPingCheck) {
			// yes yes, we're here
			@Nonnull final CompletePingCheck ping=new CompletePingCheck();
			ping.bpingid.vpingid=((StartPingCheck) m).bpingid.vpingid;
			send(ping);
			internal=true;
		}
		if (m instanceof KickUser) {
			@Nonnull final KickUser kick=(KickUser) m;
			internal=true;
			bot.shutdown("Kicked from Second Life: "+kick.buserinfo.vreason);
		}
		if (m instanceof RegionHandshake) {
			// this comes back soon after we establish the connection, and needs to be replied to explicitly (not just acked)
			//internal=true; // we no longer mark this as internal so that it can be used by the Region module
			@Nonnull final RegionHandshake r=(RegionHandshake) m;
			regionname=r.bregioninfo.vsimname.toString();
			setName("Circuit for "+bot.getUsername()+" to "+regionname);
			log=bot.getLogger("Circuit."+regionname);
			Global.regionName(regionhandle,regionname);
			final boolean register=false; // most connections register at creation since we know where we're connecting to
			// the initial circuit maybe not so much
            // handle of the simulator
            LLUUID regionuuid = r.bregioninfo2.vregionid;
			if (Debug.REGIONHANDLES) { log.log(Level.FINE,"RegionHandshake with UUID {0}", regionuuid.toUUIDString()); }
			if (firsthandshake) {
				log.log(Level.INFO,
				        "Handshake {0} cpu {1}/{2} active as {3}#{4} @ colocation {5}",
				        new Object[]{regionname,
				                     r.bregioninfo3.vcpuclassid.value,
				                     r.bregioninfo3.vcpuratio.value,
				                     r.bregioninfo3.vproductname.toString(),
				                     r.bregioninfo3.vproductsku.toString(),
				                     r.bregioninfo3.vcoloname.toString()}
				       );
				firsthandshake=false;
				bot.setConnected();
			}
			@Nonnull final RegionHandshakeReply reply=new RegionHandshakeReply();
			reply.bagentdata.vagentid=bot.getUUID();
			reply.bagentdata.vsessionid=bot.getSession();
			@Nonnull final Packet replypacket=new Packet(reply);
			replypacket.setReliable(true);
			replypacket.setZeroCode(true);
			try { send(replypacket); }
			catch (IOException e) { throw new IllegalStateException("Circuit failed during handshake, this is probably fatal",e); }
			// open our eyes
			bot.setMaxFOV();
			bot.agentUpdate();
		}
		if (m instanceof DisableSimulator) {
			if (!disconnectlogged) {
				disconnectlogged=true;
				log.log(Level.INFO,"Circuit closure was requested from Simulator {0} via DisableSimulator",regionname);
			}
			close();
			internal=true;
		}
		if (!internal) {
			// if it wasn't a circuit related packet, hand it off to the bot
			if (Debug.REGIONHANDLES && regionhandle==null) { log.info("Creating event with null RH :( "); }
			@Nonnull final Regional r;
			r=regional();
			new UDPEvent(bot,r,m,m.getName()).submit();
		}

	}

}
