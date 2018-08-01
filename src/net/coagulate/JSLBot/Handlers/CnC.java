/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.coagulate.JSLBot.Handlers;

import java.util.HashMap;
import java.util.Map;
import net.coagulate.JSLBot.Circuit;
import net.coagulate.JSLBot.CommandEvent;
import net.coagulate.JSLBot.Configuration;
import net.coagulate.JSLBot.Debug;
import net.coagulate.JSLBot.Global;
import net.coagulate.JSLBot.Handler;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.LLSD.LLSDArray;
import net.coagulate.JSLBot.LLSD.LLSDBinary;
import net.coagulate.JSLBot.LLSD.LLSDInteger;
import net.coagulate.JSLBot.LLSD.LLSDMap;
import net.coagulate.JSLBot.LLSD.LLSDString;
import net.coagulate.JSLBot.Log;
import static net.coagulate.JSLBot.Log.INFO;
import static net.coagulate.JSLBot.Log.NOTE;
import static net.coagulate.JSLBot.Log.WARN;
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

    JSLBot bot;
    Configuration config;
    
    public CnC(Configuration c) { super(c); config=c; }
    @Override
    public String toString() {
        return "Instant Message Command and Control handler for JSLBot";
    }

    @Override
    public void initialise(JSLBot ai) throws Exception {
        bot=ai;
        bot.addUDP("ImprovedInstantMessage", this);
        bot.addCommand("loadhandler", this);
        bot.addCommand("unloadhandler", this);
        bot.addImmediateXML("EnableSimulator", this);
        bot.addXML("EstablishAgentCommunication",this);
        bot.addUDP("ChatFromSimulator", this);
        bot.addUDP("AlertMessage",this);
        bot.addCommand("quit",this);
    }

    @Override
    public void processUDP(JSLBot bot, Regional region, UDPEvent event, String eventname) throws Exception {
        if (eventname.equals("AlertMessage")) {
            AlertMessage msg=(AlertMessage) event.body();
            warn(bot,"Simulator sends Alert, data message: "+msg.balertdata.vmessage.toString());
            for (AlertMessage_bAlertInfo info:msg.balertinfo) {
                warn(bot,"Warning included info:"+info.vmessage.toString()+" / "+info.vextraparams.toString());
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
        }
        if (eventname.equals("ImprovedInstantMessage")) {
            ImprovedInstantMessage m=(ImprovedInstantMessage) event.body();
            int messagetype=m.bmessageblock.vdialog.value;
            String messagetext="["+m.bmessageblock.vfromagentname.toString()+"] "+m.bmessageblock.vmessage.toString();
            // this is a HEAVILY overloaded conduit of information
            // http://wiki.secondlife.com/wiki/ImprovedInstantMessage
            switch (messagetype) {
                case 0: //Indicates a regular IM from another agent
                    processInstantMessage(m);
                    break;

                case 1: //Simple notification box with an OK button
                    Log.log(bot, NOTE, "System sends notification: "+messagetext);
                    break;

                case 2: //"Used to show a countdown notification with an OK button, deprecated now"
                    Log.log(bot,NOTE,"Deprecated countdown notification: "+messagetext);
                    break;

                case 3: //You've been invited to join a group
                    Log.log(bot,INFO,"Group invite received: "+messagetext);
                    break;

                case 4: //Inventory offer
                    Log.log(bot,INFO,"Inventory offer: "+messagetext);
                    break;

                case 5: //Accepted inventory offer
                    Log.log(bot,INFO,"Accepted inventory offer: "+messagetext);
                    break;

                case 6: //Declined inventory offer
                    Log.log(bot,INFO,"Declined inventory offer: "+messagetext);
                    break;

                case 7: //Group vote
                    Log.log(bot,NOTE,"Group vote?: "+messagetext);
                    break;

                case 8: //"A message to everyone in the agent's group, deprecated"
                    Log.log(bot,NOTE,"Deprecated group message: "+messagetext);
                    break;

                case 9: //An object is offering its inventory
                    Log.log(bot,NOTE,"Object sending inventory offer: "+messagetext);
                    break;

                case 10: //Accept an inventory offer from an object
                    Log.log(bot,NOTE,"Object inventory offer accepted: "+messagetext);
                    break;

                case 11: //Decline an inventory offer from an object
                    Log.log(bot,NOTE,"Object inventory offer declined: "+messagetext);
                    break;

                case 12: //Default --as value #0 but for offline tools
                    Log.log(bot,NOTE,"Offline message (not processed): "+messagetext);
                    break;

                case 13: //"Start a session, or add users to a session"
                    Log.log(bot,NOTE,"Session start/add: "+messagetext);
                    break;

                case 14: //"Start a session, but don't prune offline users"
                    Log.log(bot,NOTE,"Start session without prune offline: "+messagetext);
                    break;

                case 15: //Start a session with your group
                    Log.log(bot,NOTE,"Start group session: "+messagetext);
                    break;

                case 16: //Start a session without a calling card (finder or objects)
                    Log.log(bot,NOTE,"Session without calling card: "+messagetext);
                    break;

                case 17: //Send a message to a session
                    Log.log(bot,NOTE,"Message to session: "+messagetext);
                    break;

                case 18: //Leave a session
                    Log.log(bot,NOTE,"Leave session: "+messagetext);
                    break;

                case 19: //Indicates that the IM is from an object
                    Log.log(bot,NOTE,"Object IM: "+messagetext);
                    break;

                case 20: //"Sent an IM to a busy user, this is the auto response"
                    Log.log(bot,NOTE,"Busy Autoresponse: "+messagetext);
                    break;

                case 21: //Shows the message in the console and chat history
                    Log.log(bot,NOTE,"Console/Chat message: "+messagetext);
                    break;

                case 22: //Send a teleport lure
                    Log.log(bot,INFO,"Teleport Lure: "+messagetext);
                    break;

                case 23: //Response sent to the agent which inititiated a teleport invitation
                    Log.log(bot,NOTE,"TP Response type 23: "+messagetext);
                    break;

                case 24: //Response sent to the agent which inititiated a teleport invitation
                    Log.log(bot,NOTE,"TP Response type 24: "+messagetext);
                    break;

                case 25: //Godlike request teleport (only useful if you have Linden permissions)
                    Log.log(bot,WARN,"GODLIKE TELEPORT LURE: "+messagetext);
                    break;
                
                case 26: //"A placeholder type for future expansion, currently not used"
                    Log.log(bot,WARN,"Placeholder 26: "+messagetext);
                    break;

                case 27: //"Notification of a new group election, this is deprecated"
                    Log.log(bot,WARN,"Group election (deprecated): "+messagetext);
                    break;

                case 28: //IM to tell the user to go to an URL
                    Log.log(bot,NOTE,"Open URL: "+messagetext);
                    break;

                case 29: //IM for help
                    Log.log(bot,NOTE,"Help IM: "+messagetext);
                    break;

                case 30: //"IM sent automatically on call for help, sends a lure to each Helper reached"
                    Log.log(bot,NOTE,"Call for help: "+messagetext);
                    break;

                case 31: //Like an IM but won't go to email
                    Log.log(bot,NOTE,"Non emailable IM: "+messagetext);
                    break;

                case 32: //IM from a group officer to all group members
                    Log.log(bot,NOTE,"Group Officer: "+messagetext);
                    break;

                case 33: //Group notice requested
                    Log.log(bot,NOTE,"Group notice: "+messagetext);
                    break;

                case 34: //Unknown
                    Log.log(bot,NOTE,"Unknown 34: "+messagetext);
                    break;

                case 35: //Accept a group invitation
                    Log.log(bot,NOTE,"Accepted group invite: "+messagetext);
                    break;

                case 36: //Decline a group invitation
                    Log.log(bot,NOTE,"Declined group invite: "+messagetext);
                    break;

                case 37: //Unknown
                    Log.log(bot,NOTE,"Unknown 37: "+messagetext);
                    break;

                case 38: //An avatar is offering you friendship
                    Log.log(bot,NOTE,"Friendship request: "+messagetext);
                    break;

                case 39: //An avatar has accepted your friendship offer
                    Log.log(bot,NOTE,"Friendship accepted: "+messagetext);
                    break;

                case 40: //An avatar has declined your friendship offer
                    Log.log(bot,NOTE,"Friendship declined: "+messagetext);
                    break;

                case 41: //Indicates that a user has started typing
                    //Log.log(bot,DEBUG,"User is typing: "+messagetext); // beyond tedious to log
                    break;

                case 42: //Indicates that a user has stopped typing
                    //Log.log(bot,DEBUG,"User stopped typing: "+messagetext); // also beyond tedious to log
                    break;
                
                default:
                    Log.log(bot, WARN, "Unhandled Instant Message Dialog Type #"+messagetype);
                    break;
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
        String parts[]=message.split(" ");
        int index=0;
        String command=parts[0]; index++;
        String response="You do not have permission to run "+command;
        Map<String,String> params=new HashMap<>();
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
                    params.put(keyword,parameter);
                    keyword=""; parameter="";
                }
            }
        }
        response = internalCommands(command,parts,source);


        if (response==null) {
            try {
                response=new CommandEvent(bot, null, command, params,source).execute();
            }
            catch (Exception e) {
                Log.log(bot,Log.WARN,"CnC Subcommand exceptioned:"+e.toString(),e);
                response="Exception:"+e.toString();
            }
        }
        if (bot.quitting()) { note(bot,"Not sending IM response due to shutdown: "+response); }
        else { if (response!=null && !response.equals("")) { bot.im(source,">> "+response); } }
    }

    private String internalCommands(String command,String parts[],LLUUID source) {
        return null;
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
    public void processImmediateUDP(JSLBot bot, Regional region, UDPEvent event, String eventname) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void processImmediateXML(JSLBot bot, Regional region, XMLEvent event, String eventname) throws Exception {
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
                catch (Exception e) { Log.note(bot,"Failed to set up circuit to "+Global.getRegionName(handle.toLong())+" (#"+handle+")"); }
            }
        }
    }

    @Override
    public void processXML(JSLBot bot, Regional region, XMLEvent event, String eventname) throws Exception {
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
    public String execute(JSLBot bot, Regional region, CommandEvent event, String eventname, Map<String,String> parameters) throws Exception {
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
}
