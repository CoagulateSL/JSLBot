package net.coagulate.JSLBot;

/**
 * Just some flags, mostly for over verbose stuff.
 *
 * @author Iain Price
 */
public class Debug {

	public static final boolean DEBUG=true; // master on off switch

	public static final boolean AUTH=false;  // debug login
	public static final boolean CIRCUIT=false; // debug circuit setup+teardown
	public static final boolean PACKET=false; // per packet (UDP) debugging
	public static final boolean ACK=false; // debug ACKs, ack queues, resents, etc etc
	public static final boolean UNHANDLEDONCE=false; // dump unhandled messages the first time
	public static final boolean UNHANDLEDALL=false; // dump all unhandled messages
	public static final boolean DUMPXML=false; // dump XML parsing from the Event Queue
	public static final boolean NOTERETRANS=false; // fairly common and less interesting than you think
	public static final boolean DUMPRETRANS=false; // dump the content of retransmitted UDP packets
	public static final boolean EVENTQUEUE=false; // debug the stages of the event queue thread
	public static final boolean REGIONHANDLES=false; // debug region handle usage.
	public static final boolean TRACKCOMMANDS=false; // dump status changes on command events
	public static final boolean TRACKNEWOBJECTS=false; // debug when objects are discovered
}
