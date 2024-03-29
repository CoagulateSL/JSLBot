package net.coagulate.JSLBot;

import net.coagulate.Core.BuildInfo.JSLBotBuildInfo;

import javax.annotation.Nonnull;
import java.text.SimpleDateFormat;

/**
 * Some fairly arbitrary constants.
 *
 * @author Iain Price
 */
public abstract class Constants {
	
	public static final String VERSION_DATE     =
			new SimpleDateFormat("yyyy-MM-dd HH:mm").format(JSLBotBuildInfo.BUILDDATE);
	public static final String COPYRIGHT_MESSAGE="(C) jslbot@predestined.net";
	
	public static final boolean PACKET_ACCOUNTING                    =true;
	public static final boolean PACKET_ACCOUNTING_BY_MESSAGE         =true;
	public static final int     MAX_RETRIES                          =10; // login attempts
	public static final int     CIRCUIT_PING                         =30;
			// seconds of silence before we send a bodged StartPingCheck which should get a reply.
	public static final int     CIRCUIT_TIMEOUT                      =60;
			// seconds of silence before we consider a circuit dead.
	public static final long    MAX_RETRY_INTERVAL                   =5000; // maximum retry interval
	public static final long    RETRY_INTERVAL                       =2500; // milliseconds between login attempts
	public static final long    AGENT_UPDATE_FREQUENCY_MILLISECONDS  =10000; // how often to update server
	public static final int     MAX_LAUNCH_ATTEMPTS                  =5;
			// auto reconnect "safety" - do not reconnect more than this many times
	public static final int     MAX_LAUNCH_ATTEMPTS_WINDOW_SECONDS   =600;
			// in this many seconds.  if we do, just force quit.
	public static final long    ACK_TIMEOUT                          =3000;
			// how long for an unacked packet before retransmit kicks in
	public static final int     UDP_MAX_BUFFER                       =16384;
			// 16kb UDP packet.  arbitary number, probably very excessive for SL
	public static final long    BRAIN_PROCRASTINATES_FOR_MILLISECONDS=1000;
			// how long to sleep the thread on the event queue before releasing the thread for maintenance
	// tasks etc
	
	// ---------- STATICS ----------
	@Nonnull
	public static String getVersion() {
		return "JSLBot "+new SimpleDateFormat("yyyy-MM-dd HH:mm").format(JSLBotBuildInfo.BUILDDATE)+" @"+
		       JSLBotBuildInfo.COMMITID+" "+COPYRIGHT_MESSAGE;
	}
}
