package net.coagulate.JSLBot.Handlers;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.coagulate.JSLBot.Circuit;
import net.coagulate.JSLBot.CommandEvent;
import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.Debug;
import net.coagulate.JSLBot.Global;
import net.coagulate.JSLBot.Handler;
import net.coagulate.JSLBot.Handlers.Authorisation.Authorisation;
import net.coagulate.JSLBot.Handlers.Authorisation.DenyAll;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.JSLBot.CmdHelp;
import net.coagulate.JSLBot.JSLBot.ParamHelp;
import net.coagulate.JSLBot.LLSD.LLSD;
import net.coagulate.JSLBot.LLSD.LLSDArray;
import net.coagulate.JSLBot.LLSD.LLSDBinary;
import net.coagulate.JSLBot.LLSD.LLSDInteger;
import net.coagulate.JSLBot.LLSD.LLSDMap;
import net.coagulate.JSLBot.LLSD.LLSDString;
import net.coagulate.JSLBot.Log;
import static net.coagulate.JSLBot.Log.ERROR;
import static net.coagulate.JSLBot.Log.NOTE;
import static net.coagulate.JSLBot.Log.WARN;
import static net.coagulate.JSLBot.Log.datetime;
import net.coagulate.JSLBot.Packets.Messages.AlertMessage;
import net.coagulate.JSLBot.Packets.Messages.AlertMessage_bAlertInfo;
import net.coagulate.JSLBot.Packets.Messages.ChatFromSimulator;
import net.coagulate.JSLBot.Packets.Messages.ChatFromViewer;
import net.coagulate.JSLBot.Packets.Messages.ImprovedInstantMessage;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.LLVector3;
import net.coagulate.JSLBot.Packets.Types.S32;
import net.coagulate.JSLBot.Packets.Types.U8;
import net.coagulate.JSLBot.Packets.Types.Variable2;
import net.coagulate.JSLBot.Regional;
import net.coagulate.JSLBot.UDPEvent;
import net.coagulate.JSLBot.XMLEvent;

/**
 *
 * @author Iain Price
 */
public class CnC extends Handler {

    public CnC(JSLBot bot,Configuration c) {
        super(bot,c);
        String authoriser=c.get("authoriser", "OwnerOnly");
        if (authoriser.indexOf(".")==-1) { authoriser="net.coagulate.JSLBot.Handlers.Authorisation."+authoriser; }
        Authorisation auth=null;
        try {
            auth=(Authorisation) Class.forName(authoriser).getConstructor(JSLBot.class,Configuration.class).newInstance(bot,c.subspace("authorisation"));
        } catch (Exception e) {
            Log.error(bot,"Unable to load authoriser "+authoriser, e);
        }
        if (auth==null) {
            Log.error(bot,"No authorisation successfully loaded, using DenyAll, all commands will be rejected, even from you.");
            auth=new DenyAll(bot,c.subspace("authorisation"));
        }
        bot.brain().setAuth(auth);
        String homesick=c.get("homesickfor","");
        if (homesick!=null && !homesick.isEmpty()) { bot.homeSickFor(homesick); }
    }
    
    private static Date parseRegionRestart(String m) {
        if (m.split("\n").length<2) { throw new IllegalArgumentException("Expected at least 2 lines of input"); }
        String line=m.split("\n")[1];
        LLSDMap msg=(LLSDMap) new LLSD(line).getFirst();
        String region=msg.get("NAME").toString();
        long shutdown=new Date().getTime();
        if (msg.containsKey("MINUTES")) { shutdown=shutdown+((Integer.parseInt(msg.get("MINUTES").toString()))*1000*60); }
        if (msg.containsKey("SECONDS")) { shutdown=shutdown+((Integer.parseInt(msg.get("SECONDS").toString()))*1000); }
        Date when=new Date(shutdown);
        return when;
    }

    private Date evacby=null;
    

    public void alertMessageUDPDelayed(UDPEvent event) {
        AlertMessage msg=(AlertMessage) event.body();
        warn(event,"Simulator sends Alert, data message: "+msg.balertdata.vmessage.toString());
        for (AlertMessage_bAlertInfo info:msg.balertinfo) {
            String infotype=info.vmessage.toString();
            boolean handled=false;
            if (infotype.toLowerCase().contains("home")) {handled=true;} // handled by agent
            if (infotype.equals("RegionRestartMinutes") || infotype.equals("RegionRestartSeconds")) {
                handled=true;
                Date when=parseRegionRestart(info.vextraparams.toString());
                int seconds=(int) ((when.getTime()-(new Date().getTime()))/1000);
                int level = NOTE;
                if (seconds<=180) { level=WARN; }
                if (seconds<=60) {
                    level=ERROR;
                    if (evacby==null ||evacby.before(new Date())) { // if not evacuating or it was in the past
                        evacby=new Date(when.getTime()+30000); // set to 30 seconds post evacuation so we dont do this more than once
                        Map<String,String> params=new HashMap<>();
                        params.put("when",""+((int)(when.getTime()/1000)));
                        CommandEvent evacuate=new CommandEvent(bot, event.region(), "evacuate", params, null);
                        evacuate.submit();
                    }
                }
                String mins=Integer.toString(seconds/60);
                String secs=Integer.toString(seconds % 60);
                if (secs.length()==1) { secs="0"+secs; }
                if (level==ERROR) { 
                    error(event,"Simulator will shut down in "+mins+"m"+secs+"s at "+datetime.format(when));
                } else { note(event,"Simulator will shut down in "+mins+"m"+secs+"s at "+datetime.format(when)); }
            }
            if (!handled) { warn(event,"Unhandled warning included info:"+info.vmessage.toString()+" / "+info.vextraparams.toString()); }
        }
    }
    
    public void chatFromSimulatorUDPDelayed(UDPEvent event) {
        ChatFromSimulator msg = (ChatFromSimulator) event.body();
        String from=msg.bchatdata.vfromname.toString();
        LLUUID source=msg.bchatdata.vsourceid;
        LLUUID owner=msg.bchatdata.vownerid;
        int sourcetypenum=msg.bchatdata.vsourcetype.integer();
        int chattypenum=msg.bchatdata.vchattype.integer();
        int audiblenum=msg.bchatdata.vaudible.integer();
        LLVector3 pos=msg.bchatdata.vposition;
        String message=msg.bchatdata.vmessage.toString();
        String sourcetype="";
        switch (sourcetypenum) {
            case 0: sourcetype="SYSTEM"; break;
            case 1: sourcetype="Agent"; break;
            case 2: sourcetype="Object"; break;
            default: sourcetype="Unknown#"+sourcetypenum; break;
        }
        String audible="";
        switch (audiblenum) {
            case -1: audible="Inaudible"; break;
            case 0: audible="Quiet"; break;
            case 1: audible="Normal"; break;
            default: audible="Unknown#"+audiblenum; break;
        }
        String chattype="";
        switch (chattypenum) {
            case 0: chattype="Whisper"; break;
            case 1: chattype="Normal"; break;
            case 2: chattype="Shout"; break;
            case 3: chattype="Say??"; break;
            case 4: chattype="StartTyping"; break;
            case 5: chattype="StopTyping"; break;
            case 6: chattype="Debug"; break;
            case 8: chattype="OwnerSay"; break;
            default: chattype="Unknown#"+chattypenum; break;
        }
        if (chattypenum!=4 && chattypenum!=5) {
            String ownedby="";
            if (!source.equals(owner)) { 
                ownedby=" (owner:"+bot.getUserName(owner)+")";
            }
            String volume="";
            if (audiblenum!=1) {  volume="vol:"+audible+" ";}
            debug(event,"Chat ("+chattype+")"+volume+" "+sourcetype+":<"+from+">"+ownedby+":: "+message);
        }
        String prefix=config.get("publiccommandprefix","*");
        runCommands(from, source, message, prefix);
    }
    
    public void improvedInstantMessageUDPDelayed(UDPEvent event) {
        ImprovedInstantMessage m=(ImprovedInstantMessage) event.body();
        int messagetype=m.bmessageblock.vdialog.value;
        String messagetext="["+m.bmessageblock.vfromagentname.toString()+"] "+m.bmessageblock.vmessage.toString();
        // this is a HEAVILY overloaded conduit of information
        // http://wiki.secondlife.com/wiki/ImprovedInstantMessage
        switch (messagetype) {
            case 0: processInstantMessage(event,m); break;
            case 1: note(event, "System sends notification: "+messagetext); break;
            case 2: note(event,"Deprecated countdown notification: "+messagetext); break;
            case 3: info(event,"Group invite received: "+messagetext); break;
            case 4: info(event,"Inventory offer: "+messagetext); break;
            case 5: info(event,"Accepted inventory offer: "+messagetext); break;
            case 6: info(event,"Declined inventory offer: "+messagetext); break;
            case 7: note(event,"Group vote?: "+messagetext); break;
            case 8: note(event,"Deprecated group message: "+messagetext); break;
            case 9: note(event,"Object sending inventory offer: "+messagetext); break;
            case 10: note(event,"Object inventory offer accepted: "+messagetext); break;
            case 11: note(event,"Object inventory offer declined: "+messagetext); break;
            case 12: note(event,"Offline message (not processed): "+messagetext); break;
            case 13: note(event,"Session start/add: "+messagetext); break;
            case 14: note(event,"Start session without prune offline: "+messagetext); break;
            case 15: note(event,"Start group session: "+messagetext); break;
            case 16: note(event,"Session without calling card: "+messagetext); break;
            case 17: note(event,"Message to session: "+messagetext); break;
            case 18: note(event,"Leave session: "+messagetext); break;
            case 19: note(event,"Object IM: "+messagetext); break;
            case 20: note(event,"Busy Autoresponse: "+messagetext); break;
            case 21: note(event,"Console/Chat message: "+messagetext); break;
            case 22: info(event,"Teleport Lure: "+messagetext); break;
            case 23: note(event,"TP Response type 23: "+messagetext); break;
            case 24: note(event,"TP Response type 24: "+messagetext); break;
            case 25: warn(event,"GODLIKE TELEPORT LURE: "+messagetext); break;
            case 26: warn(event,"Placeholder 26: "+messagetext); break;
            case 27: warn(event,"Group election (deprecated): "+messagetext); break;
            case 28: note(event,"Open URL: "+messagetext); break;
            case 29: note(event,"Help IM: "+messagetext); break;
            case 30: note(event,"Call for help: "+messagetext); break;
            case 31: note(event,"Non emailable IM: "+messagetext); break;
            case 32: note(event,"Group Officer: "+messagetext); break;
            case 33: note(event,"Group notice: "+messagetext); break;
            case 34: note(event,"Unknown 34: "+messagetext); break;
            case 35: note(event,"Accepted group invite: "+messagetext); break;
            case 36: note(event,"Declined group invite: "+messagetext); break;
            case 37: note(event,"Unknown 37: "+messagetext); break;
            case 38: note(event,"Friendship request: "+messagetext); break;
            case 39: note(event,"Friendship accepted: "+messagetext); break;
            case 40: note(event,"Friendship declined: "+messagetext); break;
            case 41: break; //Log.log(bot,DEBUG,"User is typing: "+messagetext); // beyond tedious to log
            case 42: break; //Log.log(bot,DEBUG,"User stopped typing: "+messagetext); // also beyond tedious to log
            default: warn(event, "Unhandled Instant Message Dialog Type #"+messagetype); break;
        }

    }
    
    public void processInstantMessage(UDPEvent event,ImprovedInstantMessage m) {
        //System.out.println(m.dump());
        String from=m.bmessageblock.vfromagentname.toString();
        LLUUID source=m.bagentdata.vagentid;
        String message=m.bmessageblock.vmessage.toString();
        // extract and cut it all up
        info(event,"CnC processing instant message <"+from+"> "+message);
        String prefix=config.get("privatecommandprefix","*");
        runCommands(from,source,message,prefix);
    }

    private String parseCommand(String message,Map<String,String> paramsout1) {
        String parts[]=message.split(" ");
        int index=0;
        String command=parts[0]; index++;
        String keyword="";
        String parameter="";
        for (int i=index;i<parts.length;i++) {
            if (keyword.equals("")) {
                keyword=parts[i];
            } else {
                if (!parameter.equals("")) { parameter+=" "; }
                parameter+=parts[i];
                if ((!parameter.startsWith("\"")) || (parameter.startsWith("\"") && parameter.endsWith("\""))) {
                    if (parameter.startsWith("\"")) {
                        parameter=parameter.substring(1,parameter.length()-1);
                    }
                    paramsout1.put(keyword,parameter);
                    keyword=""; parameter="";
                }
            }
        }
        return command;
    }
    
    
    public void enableSimulatorXMLImmediate(XMLEvent event) {
        LLSDArray simulatorinfos=(LLSDArray) ((LLSDMap)event.body()).get("SimulatorInfo");
        for (Object m:simulatorinfos) {
            LLSDMap map=(LLSDMap)m;
            LLSDBinary ip=(LLSDBinary) map.get("IP");
            LLSDInteger port=(LLSDInteger) map.get("Port");
            LLSDBinary handle=(LLSDBinary) map.get("Handle");
            String numericip=ip.toIP();
            byte[] handlebytes=handle.toByte();
            if (Debug.REGIONHANDLES) { debug(event,"Asked to XML_EnableSimulator with handle "+Long.toUnsignedString(handle.toLong())); }
            new CircuitLauncher(bot,numericip,port.get(),handle.toLong()).start();
        }
    }
    
    class CircuitLauncher extends Thread {
        String numericip;
        int port;
        long handle;
        
        private CircuitLauncher(JSLBot bot,String numericip, int port, long handle) {
            this.numericip=numericip;
            this.port=port;
            this.handle=handle;
        }
        public void run() {
            try {
                bot.createCircuit(numericip,port,handle,null);
            } catch (Exception e) {
                Log.error(bot,"Failed to set up circuit to "+Global.regionName(handle)+" (#"+Long.toUnsignedString(handle)+")");
            }
            
        }
    }

    
    public void establishAgentCommunicationXMLDelayed(XMLEvent event) {
        LLSDMap body=event.map();
        String simipandport=((LLSDString)(body.get("sim-ip-and-port"))).toString();
        for (Circuit c:bot.getCircuits()) {
            if (c.getSimIPAndPort().equalsIgnoreCase(simipandport)) {
                if (Debug.EVENTQUEUE) { debug(event,"Matched ip and port to circuit for region "+c.getRegionName()); }
                String seedcaps = ((LLSDString)(body.get("seed-capability"))).toString();
                c.connectCAPS(seedcaps);
                return;
            }
        }
        error(event,"Did not find simipandport "+simipandport+" to bind the event queue to");
    }

    @CmdHelp(description = "Request the bot shutdown")
    public String quitCommand(CommandEvent command) {
        bot.shutdown("Instant Message instructed us to quit");
        return "Shutting down as requested (this message will not be delivered)";
    }
    private void runCommands(String from, LLUUID source, String message, String prefix) {
        boolean prefixok=false;
        if (prefix==null || prefix.isEmpty()) { prefixok=true; }
        if (!prefixok) { 
            if (message.startsWith(prefix)) {
                message=message.substring(prefix.length());
                prefixok=true;
            }
        }
        if (!prefixok) { return; }
        Log.note(bot,source.toUUIDString()+" <"+from+"> invokes command "+message);
        Map<String,String> params=new HashMap<>();
        String keyword=parseCommand(message,params);
        String response;
        try {
            CommandEvent command = new CommandEvent(bot, bot.getRegional(), keyword, params,source);
            command.invokerUsername(from); command.invokerUUID(source);
            response=bot.brain().auth(command);
            if (response==null) { response=command.execute(); }
        }
        catch (Exception e) {
            Log.warn(bot,"CnC Subcommand exceptioned:"+e.toString(),e);
            response="Exception:"+e.toString();
        }

        if (bot.quitting()) { Log.note(bot,"Not sending IM response due to shutdown: "+response); }
        else { if (response!=null && !response.equals("")) { bot.im(source,">> "+response); } }
    }

    @CmdHelp(description = "Causes the bot to reconnect to SL without quitting")
    public String restartCommand(CommandEvent command) {
        bot.forceReconnect();
        warn("Restart command initiated");
        return "This IM reply probably will be lost due to the restart.";
    }

    @CmdHelp(description="Send an instant message")
    public String imCommand(CommandEvent command,
            @ParamHelp(description = "UUID to message")
            String uuid,
            @ParamHelp(description = "Message to send")
            String message) {
        bot.im(new LLUUID(uuid), message);
        note("Sent IM to <"+uuid+"> "+bot.getUserName(new LLUUID(uuid))+" - "+message);
        return "IM sent";
    }
    
    @CmdHelp(description="Get Help :)  If you see this you're doing it right")
    public String helpCommand(CommandEvent commandevent,
            @ParamHelp(description="Optional command to get more info about")
            String command) {
        if (command==null || command.isEmpty()) {
            String response="";
            Set<String> unsortedcommands = bot.brain().getCommands();
            List<String> commands=new ArrayList<>();
            commands.addAll(unsortedcommands);
            Collections.sort(commands);
            for (String acommand:commands) {
                if (!response.isEmpty()) { response+=", "; } else { response="\n"; }
                acommand=acommand.substring(0,acommand.length()-"command".length());
                response+=acommand;
            }
            response+="\n\nUse 'help command <command>' for more information";
            return response;
        }
        command=command.toLowerCase();
        Method m=bot.brain().getCommand(command);
        if (m==null) { throw new IllegalArgumentException("Could not find command"); }
        String ret="\nCommand: "+command;
        if (m.getAnnotation(CmdHelp.class)!=null) { ret+="\n"+((CmdHelp)(m.getAnnotation(CmdHelp.class))).description(); }
        for (Parameter param:m.getParameters()) {
            if (!param.getType().equals(Regional.class)) {
                ret+="\n"+param.getName();
                if (param.getAnnotation(ParamHelp.class)!=null) {
                    ret+=" - "+param.getAnnotation(ParamHelp.class).description();
                }
            }
        }
        return ret;
    }
    
    @CmdHelp(description="Say a message in local chat")
    public String sayCommand(CommandEvent event,String message) { return chat(1,message); }
    @CmdHelp(description="Shout a message in local chat")
    public String shoutCommand(CommandEvent event,String message) { return chat(2,message); }
    @CmdHelp(description="Whisper a message in local chat")
    public String whisperCommand(CommandEvent event,String message) { return chat(0,message); }

    private String chat(int messagetype,String message) { return chat(messagetype,0,message); }
    private String chat(int messagetype,int channel,String message) {
        ChatFromViewer req=new ChatFromViewer(bot);
        req.bchatdata.vtype=new U8(messagetype);
        req.bchatdata.vchannel=new S32(channel);
        req.bchatdata.vmessage=new Variable2(message);
        bot.send(req,true);
        return "0 - Sent";
    }
    
    private Date homesickness=null;
    @Override
    public void maintenance() {
        if (bot.homeSickFor()==null) { return; } // we dont get homesick
        if (bot.getRegionName()==null || bot.getRegionName().isEmpty()) { return; } // we're nowhere (early login phase)
        if (homesickness!=null && homesickness.after(new Date())) { return; } // if timer set, wait for it to pass
        if (bot.getRegionName().equalsIgnoreCase(bot.homeSickFor())) { // if at home
            if (homesickness!=null) {
                homesickness=null;
                Log.debug(this,"No longer homesick");
            }
            return;
        }
        // we are not at home oO
        // if our date thing is null this is the first time we've noticed ; set first TP home at now+60
        if (homesickness==null) {
            Log.debug(this,"Bot not at home, beginning to feel home sick");
            homesickness=new Date(new Date().getTime()+60L*1000L);
            return;
        }
        // otherwise, go home
        Log.debug(this,"Bot has homesickness and is attempting to teleport home during free-will"); // specifically when the brain isn't occupied, otherwise we wouldn't be in maintenance
        new CommandEvent(bot, bot.getRegional(), "home", new HashMap<>(), null).execute();
        try { Thread.sleep(1000L); } catch (InterruptedException e) {}
        if (bot.getRegionName().equalsIgnoreCase(bot.homeSickFor())) {
            // success 
            Log.debug(this,"Bot has successfully cured its self of homesickness by going home, bot is no longer homesick");
            homesickness=null;
            return;
        }        
        // failure, retry in 5
        Log.debug(this,"Bot attempted to return home but had no luck, skulking for 5 minutes, bot remains homesick");
        homesickness=new Date(new Date().getTime()+5L*60L*1000L);
    }

    @CmdHelp(description="Manage the homesickness of this bot")
    public String homesickCommand(CommandEvent event,
            @ParamHelp(description="Name of region to long for, blank to get current, or NONE to clear")
            String region) {
        if (region==null || region.isEmpty()) {
            if (bot.homeSickFor()==null || bot.homeSickFor().isEmpty()) { return "Bot has no longing for any home"; }
            return "Bot longs for its home of '"+bot.homeSickFor()+"'";
        }
        if (region.equals("NONE")) { 
            bot.homeSickFor(null);
            config.put("homesickfor","");
            return "Bot longing for home cleared";
        }
        bot.homeSickFor(region);
        config.put("homesickfor","");
        return "Bot now longs for home of '"+bot.homeSickFor()+"'";
    }
    
}
