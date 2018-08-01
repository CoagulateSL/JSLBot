package net.coagulate.JSLBot.Handlers;

import java.util.Map;
import net.coagulate.JSLBot.CommandEvent;
import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.Handler;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Log;
import net.coagulate.JSLBot.Packets.Messages.HealthMessage;
import net.coagulate.JSLBot.Regional;
import net.coagulate.JSLBot.UDPEvent;
import net.coagulate.JSLBot.XMLEvent;

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
        ai.addImmediateUDP("HealthMessage", this);
    }

    @Override
    public void loggedIn() throws Exception {
    }

    @Override
    public void processImmediateUDP(JSLBot bot, Regional region, UDPEvent event, String eventname) throws Exception {
        if (eventname.equals("HealthMessage")) {
            HealthMessage h=(HealthMessage) event.body();
            health=h.bhealthdata.vhealth.value;
            if (verbose) { Log.log(master,Log.INFO,"Agent health is "+health); }
        }
    }

    @Override
    public void processImmediateXML(JSLBot bot, Regional region, XMLEvent event, String eventname) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String execute(JSLBot bot, Regional region, CommandEvent event, String eventname, Map<String,String> parameters) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void processUDP(JSLBot bot, Regional region, UDPEvent event, String eventname) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void processXML(JSLBot bot, Regional region, XMLEvent event, String eventname) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String help(String command) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
