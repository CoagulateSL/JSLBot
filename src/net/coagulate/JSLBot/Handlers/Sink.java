package net.coagulate.JSLBot.Handlers;

import java.util.HashSet;
import java.util.Set;
import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.Handler;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Regional;
import net.coagulate.JSLBot.UDPEvent;
import net.coagulate.JSLBot.XMLEvent;

/**
 *
 * @author Iain
 */
public class Sink extends Handler {
    public Sink(JSLBot bot,Configuration c) {super(bot,c);}
    @Override
    public String toString() {
        return "Sink handler to discard certain frequent packets of no value to us, without triggering 'unhandled packet' errors";
    }
    @Override
    public void initialise() throws Exception {
        bot.addUDP("SimStats",this);  // because we dont really care, probably.  can have multiple handlers anyway
        bot.addUDP("AvatarAppearance",this); // because we don't see things really
        bot.addUDP("AttachedSound", this); // because we certainly don't care to hear things :P
        bot.addUDP("ViewerEffect",this); // think this is stuff like look at beams etc etc (?)  since we dont really care about sight... meh
        bot.addUDP("AttachedSoundGainChange",this); // sound zzz
        bot.addUDP("RegionHandshake",this); // handled internally by circuit, though does little.  can be hooked for "on connect()" functionality
        bot.addUDP("SoundTrigger",this); // sound from "outside current region" (sounds really interesting to a bot)
        bot.addUDP("PreloadSound",this); // no sound
        bot.addUDP("LayerData",this); // has something to do with terrain/sky/cloud layers and some "internal proprietary format" - sounds near useless and a lot of effort :)
        bot.addUDP("CameraConstraint",this); // camera collision? unsure, its a 4 dimensional vector though
        bot.addUDP("AvatarAnimation",this); // as a non visual agent, this isn't interesting :P
        bot.addXML("AgentStateUpdate",this); // god level, default perms, some preferences, doesn't seem interesting.
    }

    Set<LLUUID> online=new HashSet<>();
    Set<LLUUID> offline=new HashSet<>();
    @Override
    public void loggedIn() throws Exception {
    }
    
    public String help(String command) { return "Unknown command "+command; }

    @Override
    public void processImmediateUDP(Regional region, UDPEvent event, String eventname) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void processImmediateXML(Regional region, XMLEvent event, String eventname) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void processUDP(Regional region, UDPEvent event, String eventname) throws Exception {

    }

    @Override
    public void processXML(Regional region, XMLEvent event, String eventname) throws Exception {
    }

}
