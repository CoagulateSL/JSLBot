package net.coagulate.JSLBot.Handlers;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.Event;
import net.coagulate.JSLBot.Handler;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Log;
import static net.coagulate.JSLBot.Log.INFO;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Messages.*;
import net.coagulate.JSLBot.Packets.Packet;
import net.coagulate.JSLBot.Packets.Types.LLUUID;

/**
 *
 * @author Iain
 */
public class Sink extends Handler {
    public Sink(Configuration c) {super(c);}
    @Override
    public String toString() {
        return "Sink handler to discard certain frequent packets of no value to us, without triggering 'unhandled packet' errors";
    }
    JSLBot bot;
    @Override
    public void initialise(JSLBot ai) throws Exception {
        bot=ai;
        ai.addHandler("SimStats",this);  // because we dont really care, probably.  can have multiple handlers anyway
        ai.addHandler("AvatarAppearance",this); // because we don't see things really
        ai.addHandler("AttachedSound", this); // because we certainly don't care to hear things :P
        ai.addHandler("ViewerEffect",this); // think this is stuff like look at beams etc etc (?)  since we dont really care about sight... meh
        ai.addHandler("OnlineNotification", this); // we actually notify on this oO
        ai.addHandler("OfflineNotification", this); // we actually notify on this oO
        ai.addHandler("AttachedSoundGainChange",this); // sound zzz
        ai.addHandler("RegionHandshake",this); // handled internally by circuit, though does little.  can be hooked for "on connect()" functionality
        ai.addHandler("SoundTrigger",this); // sound from "outside current region" (sounds really interesting to a bot)
        ai.addHandler("LayerData",this); // has something to do with terrain/sky/cloud layers and some "internal proprietary format" - sounds near useless and a lot of effort :)
        ai.addHandler("CameraConstraint",this); // camera collision? unsure, its a 4 dimensional vector though
        ai.addHandler("AvatarAnimation",this); // as a non visual agent, this isn't interesting :P
        ai.addHandler("XML_AgentStateUpdate",this); // god level, default perms, some preferences, doesn't seem interesting.
    }

    Set<LLUUID> online=new HashSet<>();
    Set<LLUUID> offline=new HashSet<>();
    @Override
    public void processImmediate(Event event) throws Exception {
        Message m=event.message();
        if (m!=null) { processImmediate(m); }
    }
    
    public void processImmediate(Message p) throws Exception {
    }

    @Override
    public void process(Event p) throws Exception {
        Message m=p.message();
        if (m!=null) { process(m); }
    }
    public void process(Message p) throws Exception {
        if (p instanceof OnlineNotification) {
            List<OnlineNotification_bAgentBlock> agents=((OnlineNotification)p).bagentblock;
            for (OnlineNotification_bAgentBlock block:agents) {
                LLUUID uuid=block.vagentid;
                Log.log(bot, INFO, "Friend ONLINE: "+bot.getDisplayName(uuid)+" ("+bot.getUserName(uuid)+") ["+uuid.toUUIDString()+"]");
                offline.remove(uuid);
                online.add(uuid);
            }
        }
        if (p instanceof OfflineNotification) {
            List<OfflineNotification_bAgentBlock> agents=((OfflineNotification)p).bagentblock;
            for (OfflineNotification_bAgentBlock block:agents) {
                LLUUID uuid=block.vagentid;
                Log.log(bot, INFO, "Friend offline: "+bot.getDisplayName(uuid)+" ("+bot.getUserName(uuid)+") ["+uuid.toUUIDString()+"]");
                online.remove(uuid);
                offline.add(uuid);
            }
        }
    }
    @Override
    public String execute(String command, Map<String, String> parameters) throws Exception {
        return null;
    }

    @Override
    public void loggedIn() throws Exception {
    }
    
    public String help(String command) { return "Unknown command "+command; }
}
