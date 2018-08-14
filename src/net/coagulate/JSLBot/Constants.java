package net.coagulate.JSLBot;

/**  Some fairly arbitrary constants.
 *
 * @author Iain Price
 */
public abstract class Constants {

    public static final int MAX_RETRIES = 10; // login attempts
    public static final int CIRCUIT_PING = 30; // seconds of silence before we send a bodged StartPingCheck which should get a reply.
    public static final int CIRCUIT_TIMEOUT = 60; // seconds of silence before we consider a circuit dead.
    public static final long MAX_RETRY_INTERVAL = 5000; // maximum retry interval
    public static final long RETRY_INTERVAL = 2500; // milliseconds between login attempts
    public static final long AGENT_UPDATE_FREQUENCY_MILLISECONDS = 2500; // how often to update server
    public static final int MAX_LAUNCH_ATTEMPTS=5; // auto reconnect "safety" - do not reconnect more than this many times
    public static final int MAX_LAUNCH_ATTEMPTS_WINDOW_SECONDS=600; // in this many seconds.  if we do, just force quit.
    
}
