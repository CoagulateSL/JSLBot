package net.coagulate.JSLBot.Handlers;

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
 * @author Iain Price
 */
public class Health extends Handler {

    JSLBot master;
    boolean verbose=false;
    float health=0;
    public Health(JSLBot bot,Configuration c) { super(bot,c); this.verbose=Boolean.parseBoolean(c.get("verbose","false")); }
    @Override
    public String toString() {
        return "Health message handler";
    }

    @Override
    public void initialise() throws Exception {
        bot.addImmediateUDP("HealthMessage", this);
    }

    @Override
    public void loggedIn() throws Exception {
    }

    @Override
    public void processImmediateUDP(Regional region, UDPEvent event, String eventname) throws Exception {
        if (eventname.equals("HealthMessage")) {
            HealthMessage h=(HealthMessage) event.body();
            health=h.bhealthdata.vhealth.value;
            if (verbose) { Log.log(master,Log.INFO,"Agent health is "+health); }
        }
    }

    @Override
    public void processImmediateXML(Regional region, XMLEvent event, String eventname) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void processUDP(Regional region, UDPEvent event, String eventname) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void processXML(Regional region, XMLEvent event, String eventname) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
