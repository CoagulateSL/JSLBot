/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.coagulate.JSLBot.Handlers;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.coagulate.JSLBot.Circuit;
import net.coagulate.JSLBot.CommandEvent;
import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.Debug;
import net.coagulate.JSLBot.Global;
import net.coagulate.JSLBot.Handler;
import net.coagulate.JSLBot.Handlers.Authorisation.Authorisation;
import net.coagulate.JSLBot.Handlers.Authorisation.DenyAll;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.LLSD.LLSD;
import net.coagulate.JSLBot.LLSD.LLSDArray;
import net.coagulate.JSLBot.LLSD.LLSDBinary;
import net.coagulate.JSLBot.LLSD.LLSDInteger;
import net.coagulate.JSLBot.LLSD.LLSDMap;
import net.coagulate.JSLBot.LLSD.LLSDString;
import net.coagulate.JSLBot.Log;
import static net.coagulate.JSLBot.Log.ERROR;
import static net.coagulate.JSLBot.Log.INFO;
import static net.coagulate.JSLBot.Log.NOTE;
import static net.coagulate.JSLBot.Log.WARN;
import static net.coagulate.JSLBot.Log.datetime;
import static net.coagulate.JSLBot.Log.debug;
import static net.coagulate.JSLBot.Log.log;
import static net.coagulate.JSLBot.Log.note;
import static net.coagulate.JSLBot.Log.warn;
import net.coagulate.JSLBot.Packets.Messages.AlertMessage;
import net.coagulate.JSLBot.Packets.Messages.AlertMessage_bAlertInfo;
import net.coagulate.JSLBot.Packets.Messages.ChatFromSimulator;
import net.coagulate.JSLBot.Packets.Messages.ImprovedInstantMessage;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.LLVector3;
import net.coagulate.JSLBot.Regional;
import net.coagulate.JSLBot.UDPEvent;
import net.coagulate.JSLBot.XMLEvent;

/**
 *
 * @author Iain
 */
public class CnC extends Handler {

    private Authorisation auth=null;
    public CnC(JSLBot bot,Configuration c) {
        super(bot,c);
        String authoriser=c.get("authoriser", "OwnerOnly");
        if (authoriser.indexOf(".")==-1) { authoriser="net.coagulate.JSLBot.Handlers.Authorisation."+authoriser; }
        try {
            auth=(Authorisation) Class.forName(authoriser).getConstructor(JSLBot.class,Configuration.class).newInstance(bot,c.subspace("authorisation"));
        } catch (Exception e) {
            Log.error(bot,"Unable to load authoriser "+authoriser, e);
        }
        if (auth==null) {
            Log.error(bot,"No authorisation successfully loaded, using DenyAll, all commands will be rejected, even from you.");
            auth=new DenyAll(bot,c.subspace("authorisation"));
        }
    }
    @Override
    public String toString() {
        return "Instant Message Command and Control handler for JSLBot";
    }

    @Override
    public void initialise() throws Exception {
        bot.addUDP("ImprovedInstantMessage", this);
        bot.addCommand("loadhandler", this);
        bot.addCommand("unloadhandler", this);
        bot.addImmediateXML("EnableSimulator", this);
        bot.addXML("EstablishAgentCommunication",this);
        bot.addUDP("ChatFromSimulator", this);
        bot.addUDP("AlertMessage",this);
        bot.addCommand("quit",this);
    }

    public static Date parseRegionRestart(String m) throws IOException {
        if (m.split("\n").length<2) { throw new IllegalArgumentException("Expected at least 2 lines of input"); }
        String line=m.split("\n")[1];
        LLSDMap msg=(LLSDMap) new LLSD(line).getFirst();
        String region=msg.get("NAME").toString();
        long shutdown=new Date().getTime();
        if (msg.containsKey("MINUTES")) { shutdown=shutdown+((Integer.parseInt(msg.get("MINUTES").toString()))*1000*60); }
        if (msg.containsKey("SECONDS")) { shutdown=shutdown+((Integer.parseInt(msg.get("SECONDS").toString()))*1000); }
        Date when=new Date(shutdown);
        System.out.println(when.toString());
        return when;
    }
    private Date evacby=null;
    @Override
    public void processUDP(Regional region, UDPEvent event, String eventname) throws Exception {
        if (eventname.equals("AlertMessage")) {
            AlertMessage msg=(AlertMessage) event.body();
            warn(bot,"Simulator sends Alert, data message: "+msg.balertdata.vmessage.toString());
            for (AlertMessage_bAlertInfo info:msg.balertinfo) {
                String infotype=info.vmessage.toString();
                boolean handled=false;
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
                            CommandEvent evacuate=new CommandEvent(bot, region, "evacuate", params, null);
                            evacuate.submit();
                        }
                    }
                    String mins=Integer.toString(seconds/60);
                    String secs=Integer.toString(seconds % 60);
                    if (secs.length()==1) { secs="0"+secs; }
                    log(bot,level,"Simulator will shut down in "+mins+"m"+secs+"s at "+datetime.format(when));
                }
                if (!handled) { warn(bot,"Unhandled warning included info:"+info.vmessage.toString()+" / "+info.vextraparams.toString()); }
            }
        }
        if (eventname.equals("ChatFromSimulator")) {
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
                Log.debug(bot,"Chat ("+chattype+")"+volume+" "+sourcetype+":<"+from+">"+ownedby+":: "+message);
            }
            String prefix=config.get("publiccommandprefix","*");
            runCommands(from, source, message, prefix);
        }
        if (eventname.equals("ImprovedInstantMessage")) {
            ImprovedInstantMessage m=(ImprovedInstantMessage) event.body();
            int messagetype=m.bmessageblock.vdialog.value;
            String messagetext="["+m.bmessageblock.vfromagentname.toString()+"] "+m.bmessageblock.vmessage.toString();
            // this is a HEAVILY overloaded conduit of information
            // http://wiki.secondlife.com/wiki/ImprovedInstantMessage
            switch (messagetype) {
                case 0: processInstantMessage(m); break;
                case 1: Log.log(bot, NOTE, "System sends notification: "+messagetext); break;
                case 2: Log.log(bot,NOTE,"Deprecated countdown notification: "+messagetext); break;
                case 3: Log.log(bot,INFO,"Group invite received: "+messagetext); break;
                case 4: Log.log(bot,INFO,"Inventory offer: "+messagetext); break;
                case 5: Log.log(bot,INFO,"Accepted inventory offer: "+messagetext); break;
                case 6: Log.log(bot,INFO,"Declined inventory offer: "+messagetext); break;
                case 7: Log.log(bot,NOTE,"Group vote?: "+messagetext); break;
                case 8: Log.log(bot,NOTE,"Deprecated group message: "+messagetext); break;
                case 9: Log.log(bot,NOTE,"Object sending inventory offer: "+messagetext); break;
                case 10: Log.log(bot,NOTE,"Object inventory offer accepted: "+messagetext); break;
                case 11: Log.log(bot,NOTE,"Object inventory offer declined: "+messagetext); break;
                case 12: Log.log(bot,NOTE,"Offline message (not processed): "+messagetext); break;
                case 13: Log.log(bot,NOTE,"Session start/add: "+messagetext); break;
                case 14: Log.log(bot,NOTE,"Start session without prune offline: "+messagetext); break;
                case 15: Log.log(bot,NOTE,"Start group session: "+messagetext); break;
                case 16: Log.log(bot,NOTE,"Session without calling card: "+messagetext); break;
                case 17: Log.log(bot,NOTE,"Message to session: "+messagetext); break;
                case 18: Log.log(bot,NOTE,"Leave session: "+messagetext); break;
                case 19: Log.log(bot,NOTE,"Object IM: "+messagetext); break;
                case 20: Log.log(bot,NOTE,"Busy Autoresponse: "+messagetext); break;
                case 21: Log.log(bot,NOTE,"Console/Chat message: "+messagetext); break;
                case 22: Log.log(bot,INFO,"Teleport Lure: "+messagetext); break;
                case 23: Log.log(bot,NOTE,"TP Response type 23: "+messagetext); break;
                case 24: Log.log(bot,NOTE,"TP Response type 24: "+messagetext); break;
                case 25: Log.log(bot,WARN,"GODLIKE TELEPORT LURE: "+messagetext); break;
                case 26: Log.log(bot,WARN,"Placeholder 26: "+messagetext); break;
                case 27: Log.log(bot,WARN,"Group election (deprecated): "+messagetext); break;
                case 28: Log.log(bot,NOTE,"Open URL: "+messagetext); break;
                case 29: Log.log(bot,NOTE,"Help IM: "+messagetext); break;
                case 30: Log.log(bot,NOTE,"Call for help: "+messagetext); break;
                case 31: Log.log(bot,NOTE,"Non emailable IM: "+messagetext); break;
                case 32: Log.log(bot,NOTE,"Group Officer: "+messagetext); break;
                case 33: Log.log(bot,NOTE,"Group notice: "+messagetext); break;
                case 34: Log.log(bot,NOTE,"Unknown 34: "+messagetext); break;
                case 35: Log.log(bot,NOTE,"Accepted group invite: "+messagetext); break;
                case 36: Log.log(bot,NOTE,"Declined group invite: "+messagetext); break;
                case 37: Log.log(bot,NOTE,"Unknown 37: "+messagetext); break;
                case 38: Log.log(bot,NOTE,"Friendship request: "+messagetext); break;
                case 39: Log.log(bot,NOTE,"Friendship accepted: "+messagetext); break;
                case 40: Log.log(bot,NOTE,"Friendship declined: "+messagetext); break;
                case 41: break; //Log.log(bot,DEBUG,"User is typing: "+messagetext); // beyond tedious to log
                case 42: break; //Log.log(bot,DEBUG,"User stopped typing: "+messagetext); // also beyond tedious to log
                default: Log.log(bot, WARN, "Unhandled Instant Message Dialog Type #"+messagetype); break;
            }
            
        }
    }
    
    public void processInstantMessage(ImprovedInstantMessage m) throws Exception {
        //System.out.println(m.dump());
        String from=m.bmessageblock.vfromagentname.toString();
        LLUUID source=m.bagentdata.vagentid;
        String message=m.bmessageblock.vmessage.toString();
        // extract and cut it all up
        log(bot,INFO,"CnC processing instant message <"+from+"> "+message);
        runCommands(from,source,message,"");
    }

    private String internalCommands(String command,String parts[],LLUUID source) {
        return null;
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
        return keyword;
    }
    
    @Override
    public void loggedIn() throws Exception {
    }
    
    public String help(String command) {
        if (command.equals("quit")) { return "calls the shutdown() handler for the bot"; }
        if (command.equals("loadhandler")) { return "loadhandler class <classname>\nLoads the named handler into the bot."; }
        if (command.equals("unloadhandler")) { return "unloadhandler class <classname>\nUnloads the named handler from the bot."; }
        return "Unknown command "+command;
    }

    @Override
    public void processImmediateUDP(Regional region, UDPEvent event, String eventname) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void processImmediateXML(Regional region, XMLEvent event, String eventname) throws Exception {
        if (eventname.equals("EnableSimulator")) {
            LLSDArray simulatorinfos=(LLSDArray) ((LLSDMap)event.body()).get("SimulatorInfo");
            for (Object m:simulatorinfos) {
                LLSDMap map=(LLSDMap)m;
                LLSDBinary ip=(LLSDBinary) map.get("IP");
                LLSDInteger port=(LLSDInteger) map.get("Port");
                LLSDBinary handle=(LLSDBinary) map.get("Handle");
                String numericip=ip.toIP();
                byte[] handlebytes=handle.toByte();
                if (Debug.REGIONHANDLES) { debug(bot,"Asked to XML_EnableSimulator with handle "+Long.toUnsignedString(handle.toLong())); }
                try { bot.createCircuit(numericip,port.get(),handle.toLong(),null); }
                catch (Exception e) { Log.note(bot,"Failed to set up circuit to "+Global.regionName(handle.toLong())+" (#"+Long.toUnsignedString(handle.toLong())+")"); }
            }
        }
    }

    @Override
    public void processXML(Regional region, XMLEvent event, String eventname) throws Exception {
        if (eventname.equals("EstablishAgentCommunication")) {
            LLSDMap body=event.map();
            String simipandport=((LLSDString)(body.get("sim-ip-and-port"))).toString();
            for (Circuit c:bot.getCircuits()) {
                if (c.getSimIPAndPort().equalsIgnoreCase(simipandport)) {
                    if (Debug.EVENTQUEUE) { debug(bot,"Matched ip and port to circuit for region "+c.getRegionName()); }
                    c.connectCAPS(((LLSDString)(body.get("seed-capability"))).toString());
                }
            }
        }
    }

    @Override
    public String execute(Regional region, CommandEvent event, String eventname, Map<String,String> parameters) throws Exception {
        if (eventname.equalsIgnoreCase("quit")) {
            bot.shutdown("Instant Message instructed us to quit");
            return "Shutting down as requested (this message will not be delivered)";
        }
        if (eventname.equalsIgnoreCase("loadhandler")) {
            String handlername=event.parameters().get("class");
            Handler h=bot.register(handlername);
            return "Module "+h.getClass().getName()+" loaded";
        }
        return null;
    }

    private void runCommands(String from, LLUUID source, String message, String prefix) throws IOException {
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
            CommandEvent command = new CommandEvent(bot, null, keyword, params,source);
            command.invokerUsername(from); command.invokerUUID(source);
            response=auth.approve(command);
            if (response==null) { response=command.execute(); }
        }
        catch (Exception e) {
            Log.log(bot,Log.WARN,"CnC Subcommand exceptioned:"+e.toString(),e);
            response="Exception:"+e.toString();
        }

        if (bot.quitting()) { note(bot,"Not sending IM response due to shutdown: "+response); }
        else { if (response!=null && !response.equals("")) { bot.im(source,">> "+response); } }
    }

    




    
}
