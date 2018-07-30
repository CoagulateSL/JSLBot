package net.coagulate.JSLBot.Handlers;

import java.util.Map;
import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.Event;
import net.coagulate.JSLBot.Handler;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Log;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Messages.HealthMessage;
import net.coagulate.JSLBot.Packets.Packet;

/**  Swallows health messages, and even stores the health.
 * If such things are interesting.
 * @author Iain Price <git@predestined.net>
 */
public class Health extends Handler {

    JSLBot master;
    boolean verbose=false;
    float health=0;
    public Health(Configuration c) { super(c); this.verbose=Boolean.parseBoolean(c.get("verbose","false")); }
    @Override
    public String toString() {
        return "Health message handler";
    }

    @Override
    public void initialise(JSLBot ai) throws Exception {
        master=ai;
        ai.addHandler("HealthMessage", this);
    }
    
    @Override
    public void process(Event event) throws Exception {
        Message m=event.message();
        if (m!=null) { process(m); }
    }    
    public void process(Message p) {
        if (p instanceof HealthMessage) {
            HealthMessage h=(HealthMessage) p;
            health=h.bhealthdata.vhealth.value;
            if (verbose) { Log.log(master,Log.INFO,"Agent health is "+health); }
        }
    }

    @Override
    public void processImmediate(Event p) throws Exception {
    }

    @Override
    public String execute(String command, Map<String, String> parameters) throws Exception {
        return "";
    }

    @Override
    public void loggedIn() throws Exception {
    }
    public String help(String command) { 
        if (command.equals("verbosehealth")) { return "verbosehealth\ntoggle verbose health reporting to log files (currently "+verbose+" ((NOTIMP)))"; }
        return "Unknown command "+command;
    }
}
