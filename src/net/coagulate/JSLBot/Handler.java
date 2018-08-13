package net.coagulate.JSLBot;

/** General template for a Handler and the various methods it may receive events through.
 *
 * @author Iain Prive
 */
public abstract class Handler {
    protected JSLBot bot;    
    protected Configuration config;
    
    public Handler(JSLBot bot,Configuration config){this.bot=bot; this.config=config;}
    @Override
    /** Name of the handler */
    public abstract String toString();
    /** Initialisation hook, called before login, use to register Event Handlers with the bot */
    public abstract void initialise() throws Exception;
    // handler for IMMEDIATE mode
    // DO NOT USE OR IMPLEMENT UNLESS YOU ABSOLUTELY HAVE TO, KNOW WHY IT NEEDS TO BE HERE, AND UNDERSTAND WHY THERE IS A BIG CAPS WARNING HERE :P
    // there are explanations of the threading model in JSLBot.java, go read those, if you still dont get it, seek advice
    // messing up processImmediate will do horrible things to your bot, like cause it to get disconnected, or just timeout/deadlock
    /** IMMEDIATE mode handler for UDP packets */
    public abstract void processImmediateUDP(Regional region, UDPEvent event, String eventname) throws Exception;
    /** IMMEDIATE mode handler for XML messages */
    public abstract void processImmediateXML(Regional region, XMLEvent event, String eventname) throws Exception;
    /** Queued handler for UDP packets */
    public abstract void processUDP(Regional region, UDPEvent event, String eventname) throws Exception;
    /** Queued handler for XML events */
    public abstract void processXML(Regional region, XMLEvent event, String eventname) throws Exception;
    /** Hook for post login activities */
    public abstract void loggedIn() throws Exception;
    
}
