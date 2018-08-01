package net.coagulate.JSLBot;

/**  Some fairly arbitrary constants.
 *
 * @author Iain Price <git@predestined.net>
 */
public abstract class Constants {

    public static final int MAX_RETRIES = 10; // login attempts
    public static final int CIRCUIT_TIMEOUT = 30; // seconds of silence before we consider a circuit dead.
    public static final long MAX_RETRY_INTERVAL = 5000; // maximum retry interval
    public static final long RETRY_INTERVAL = 2500; // milliseconds between login attempts
    
}