package net.coagulate.JSLBot.Handlers;

import net.coagulate.JSLBot.*;
import net.coagulate.JSLBot.Handlers.Authorisation.Authorisation;
import net.coagulate.JSLBot.Handlers.Authorisation.DenyAll;
import net.coagulate.JSLBot.JSLBot.CmdHelp;
import net.coagulate.JSLBot.JSLBot.Param;
import net.coagulate.JSLBot.LLSD.*;
import net.coagulate.JSLBot.Packets.Messages.*;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.logging.Level.*;

/**
 * @author Iain Price
 */
public class CnC extends Handler {

	public static final DateFormat datetime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	@Nullable
	private Date evacby;
	private int pauseserial;
	@Nullable
	private Date homesickness;

	public CnC(@Nonnull final JSLBot bot,
	           @Nonnull final Configuration c) {
		super(bot,c);
		@Nonnull String authoriser=c.get("authoriser","OwnerOnly");
		if (!authoriser.contains(".")) { authoriser="net.coagulate.JSLBot.Handlers.Authorisation."+authoriser; }
		@Nullable Authorisation auth=null;
		try {
			auth=(Authorisation) Class.forName(authoriser).getConstructor(JSLBot.class,Configuration.class).newInstance(bot,c.subspace("authorisation"));
		}
		catch (@Nonnull final Exception e) {
			log.log(SEVERE,"Unable to load authoriser "+authoriser,e);
		}
		if (auth==null) {
			log.warning("No authorisation successfully loaded, using DenyAll, all commands will be rejected, even from you.");
			auth=new DenyAll(bot,c.subspace("authorisation"));
		}
		bot.brain().setAuth(auth);
		@Nonnull final String homesick=c.get("homesickfor","");
		if (!homesick.isEmpty()) { bot.homeSickFor(homesick); }
	}

	// ----- Internal Statics -----
	@Nonnull
	private static Date parseRegionRestart(@Nonnull final String m) {
		if (m.split("\n").length<2) { throw new IllegalArgumentException("Expected at least 2 lines of input"); }
		final String line=m.split("\n")[1];
		@Nonnull final LLSDMap msg=(LLSDMap) new LLSD(line).getFirst();
		if (msg==null) { throw new IllegalArgumentException("Failed to parse LLSDMap from region restart message"); }
		final String region=msg.get("NAME").toString();
		long shutdown=new Date().getTime();
		if (msg.containsKey("MINUTES")) {
			shutdown=shutdown+((Integer.parseInt(msg.get("MINUTES").toString()))*1000*60);
		}
		if (msg.containsKey("SECONDS")) { shutdown=shutdown+((Integer.parseInt(msg.get("SECONDS").toString()))*1000); }
		return new Date(shutdown);
	}

	// ---------- INSTANCE ----------
	public void alertMessageUDPDelayed(@Nonnull final UDPEvent event) {
		@Nonnull final AlertMessage msg=(AlertMessage) event.body();
		log.warning("Simulator "+event.region().circuit()+" sends Alert, data message: "+msg.balertdata.vmessage);
		for (@Nonnull final AlertMessage_bAlertInfo info: msg.balertinfo) {
			@Nonnull final String infotype=info.vmessage.toString();
			boolean handled=false;
			if (infotype.toLowerCase().contains("home")) {handled=true;} // handled by agent
			if ("RegionRestartMinutes".equals(infotype) || "RegionRestartSeconds".equals(infotype)) {
				handled=true;
				@Nonnull final Date when=parseRegionRestart(info.vextraparams.toString());
				final int seconds=(int) ((when.getTime()-(new Date().getTime()))/1000);
				@Nonnull Level level=INFO;
				if (seconds<=180) { level=WARNING; }
				if (seconds<=60) {
					level=SEVERE;
					if (evacby==null || evacby.before(new Date())) { // if not evacuating or it was in the past
						evacby=new Date(when.getTime()+30000); // set to 30 seconds post evacuation so we dont do this more than once
						@Nonnull final Map<String,String> params=new HashMap<>();
						params.put("when",""+((int) (when.getTime()/1000)));
						@Nullable final CommandEvent evacuate=new CommandEvent(bot,event.region(),"evacuate",params,null);
						evacuate.submit();
					}
				}
				@Nonnull final String mins=Integer.toString(seconds/60);
				@Nonnull String secs=Integer.toString(seconds%60);
				if (secs.length()==1) { secs="0"+secs; }
				log.log(level,"Simulator "+event.region().circuit()+" will shut down in "+mins+"m"+secs+"s at "+datetime.format(when));
			}
			if (!handled) {
				log.warning("Unhandled warning from "+event.region().circuit()+" included info:"+info.vmessage+" / "+info.vextraparams);
			}
		}
	}

	public void chatFromSimulatorUDPDelayed(@Nonnull final UDPEvent event) {
		@Nonnull final ChatFromSimulator msg=(ChatFromSimulator) event.body();
		@Nonnull final String from=msg.bchatdata.vfromname.toString();
		@Nonnull final LLUUID source=msg.bchatdata.vsourceid;
		@Nonnull final LLUUID owner=msg.bchatdata.vownerid;
		final int sourcetypenum=msg.bchatdata.vsourcetype.integer();
		final int chattypenum=msg.bchatdata.vchattype.integer();
		final int audiblenum=msg.bchatdata.vaudible.integer();
		@Nonnull final LLVector3 pos=msg.bchatdata.vposition;
		@Nonnull final String message=msg.bchatdata.vmessage.toString();
		@Nonnull final String sourcetype;
		switch (sourcetypenum) {
			case 0:
				sourcetype="SYSTEM";
				break;
			case 1:
				sourcetype="Agent";
				break;
			case 2:
				sourcetype="Object";
				break;
			default:
				sourcetype="Unknown#"+sourcetypenum;
				break;
		}
		@Nonnull final String audible;
		switch (audiblenum) {
			case -1:
				audible="Inaudible";
				break;
			case 0:
				audible="Quiet";
				break;
			case 1:
				audible="Normal";
				break;
			default:
				audible="Unknown#"+audiblenum;
				break;
		}
		@Nonnull final String chattype;
		switch (chattypenum) {
			case 0:
				chattype="Whisper";
				break;
			case 1:
				chattype="Normal";
				break;
			case 2:
				chattype="Shout";
				break;
			case 3:
				chattype="Say??";
				break;
			case 4:
				chattype="StartTyping";
				break;
			case 5:
				chattype="StopTyping";
				break;
			case 6:
				chattype="Debug";
				break;
			case 8:
				chattype="OwnerSay";
				break;
			default:
				chattype="Unknown#"+chattypenum;
				break;
		}
		if (chattypenum!=4 && chattypenum!=5) {
			@Nonnull String ownedby="";
			if (!source.equals(owner)) {
				ownedby=" (owner:"+bot.getUserName(owner)+")";
			}
			@Nonnull String volume="";
			if (audiblenum!=1) { volume="vol:"+audible+" ";}
			log.fine("Chat ("+chattype+")"+volume+" "+sourcetype+":<"+from+">"+ownedby+":: "+message);
		}
		@Nonnull final String prefix=config.get("publiccommandprefix","*");
		runCommands(from,source,message,prefix,false);
	}

	public void improvedInstantMessageUDPDelayed(@Nonnull final UDPEvent event) {
		@Nonnull final ImprovedInstantMessage m=(ImprovedInstantMessage) event.body();
		final int messagetype=m.bmessageblock.vdialog.value;
		@Nonnull final String messagetext="["+m.bmessageblock.vfromagentname+"] "+m.bmessageblock.vmessage;
		// this is a HEAVILY overloaded conduit of information
		// http://wiki.secondlife.com/wiki/ImprovedInstantMessage
		switch (messagetype) {
			case 0:
				processInstantMessage(event,m);
				break;
			case 1:
				log.info("System sends notification: "+messagetext);
				break;
			case 2:
				log.info("Deprecated countdown notification: "+messagetext);
				break;
			case 3:
				log.info("Group invite received: "+messagetext);
				break;
			case 4:
				log.info("Inventory offer: "+messagetext);
				break;
			case 5:
				log.info("Accepted inventory offer: "+messagetext);
				break;
			case 6:
				log.info("Declined inventory offer: "+messagetext);
				break;
			case 7:
				log.info("Group vote?: "+messagetext);
				break;
			case 8:
				log.info("Deprecated group message: "+messagetext);
				break;
			case 9:
				log.info("Object sending inventory offer: "+messagetext);
				break;
			case 10:
				log.info("Object inventory offer accepted: "+messagetext);
				break;
			case 11:
				log.info("Object inventory offer declined: "+messagetext);
				break;
			case 12:
				log.info("Offline message (not processed): "+messagetext);
				break;
			case 13:
				log.info("Session start/add: "+messagetext);
				break;
			case 14:
				log.info("Start session without prune offline: "+messagetext);
				break;
			case 15:
				log.info("Start group session: "+messagetext);
				break;
			case 16:
				log.info("Session without calling card: "+messagetext);
				break;
			case 17:
				log.info("Message to session: "+messagetext);
				break;
			case 18:
				log.info("Leave session: "+messagetext);
				break;
			case 19:
				@Nonnull final String key = bot.getConfig().get("secretkey", "");
				if (key!=null && key.length()>=4) {
					// maybe we let this be a command
					// object IMs start with their name, in square brackets, apparently
					@Nonnull final Matcher matches = Pattern.compile("\\[(.*)\\] (.*)", Pattern.DOTALL).matcher(messagetext);
					if (matches.matches()) {
						final String objectName = matches.group(1);
						final String objectMessage = matches.group(2);
						if (objectMessage.startsWith("*" + key + " ")) {
							@Nonnull final String command = objectMessage.substring(key.length() + 2);
							log.info("Executing Object [" + objectName + "] IM: " + command);
							runCommands(objectName, null, command, null, true);
							break;
						}
					}
				}
				log.info("Object IM: "+messagetext);
				break;
			case 20:
				log.info("Busy Autoresponse: "+messagetext);
				break;
			case 21:
				log.info("Console/Chat message: "+messagetext);
				break;
			case 22:
				log.info("Teleport Lure: "+messagetext);
				break;
			case 23:
				log.info("TP Response type 23: "+messagetext);
				break;
			case 24:
				log.info("TP Response type 24: "+messagetext);
				break;
			case 25:
				log.warning("GODLIKE TELEPORT LURE: "+messagetext);
				break;
			case 26:
				log.warning("Placeholder 26: "+messagetext);
				break;
			case 27:
				log.warning("Group election (deprecated): "+messagetext);
				break;
			case 28:
				log.info("Open URL: "+messagetext);
				break;
			case 29:
				log.info("Help IM: "+messagetext);
				break;
			case 30:
				log.info("Call for help: "+messagetext);
				break;
			case 31:
				log.info("Non emailable IM: "+messagetext);
				break;
			case 32:
				log.info("Group Officer: "+messagetext);
				break;
			case 33:
				log.info("Group notice: "+messagetext);
				break;
			case 34:
				log.info("Unknown 34: "+messagetext);
				break;
			case 35:
				log.info("Accepted group invite: "+messagetext);
				break;
			case 36:
				log.info("Declined group invite: "+messagetext);
				break;
			case 37:
				log.info("Unknown 37: "+messagetext);
				break;
			case 38:
				log.info("Friendship request: "+messagetext);
				break;
			case 39:
				log.info("Friendship accepted: "+messagetext);
				break;
			case 40:
				log.info("Friendship declined: "+messagetext);
				break;
			case 41:
				break; //Log.log(bot,DEBUG,"User is typing: "+messagetext); // beyond tedious to log
			case 42:
				break; //Log.log(bot,DEBUG,"User stopped typing: "+messagetext); // also beyond tedious to log
			default:
				log.warning("Unhandled Instant Message Dialog Type #"+messagetype);
				break;
		}

	}

	public void processInstantMessage(final UDPEvent event,
	                                  @Nonnull final ImprovedInstantMessage m) {
		//System.out.println(m.dump());
		@Nonnull final String from=m.bmessageblock.vfromagentname.toString();
		final LLUUID source=m.bagentdata.vagentid;
		@Nonnull final String message=m.bmessageblock.vmessage.toString();
		// extract and cut it all up
		log.info("CnC processing instant message <"+from+"> "+message);
		@Nonnull final String prefix=config.get("privatecommandprefix","*");
		runCommands(from,source,message,prefix,false);
	}

	public void enableSimulatorXMLImmediate(@Nonnull final XMLEvent event) {
		@Nonnull final LLSDArray simulatorinfos=(LLSDArray) ((LLSDMap) event.body()).get("SimulatorInfo");
		for (final Object m: simulatorinfos) {
			@Nonnull final LLSDMap map=(LLSDMap) m;
			@Nonnull final LLSDBinary ip=(LLSDBinary) map.get("IP");
			@Nonnull final LLSDInteger port=(LLSDInteger) map.get("Port");
			@Nonnull final LLSDBinary handle=(LLSDBinary) map.get("Handle");
			@Nonnull final String numericip=ip.toIP();
			final byte[] handlebytes=handle.toByte();
			if (Debug.REGIONHANDLES) {
				log.fine("Asked to XML_EnableSimulator with handle "+Long.toUnsignedString(handle.toLong()));
			}
			new CircuitLauncher(bot,numericip,port.get(),handle.toLong()).start();
		}
	}

	public void establishAgentCommunicationXMLDelayed(@Nonnull final XMLEvent event) {
		@Nonnull final LLSDMap body=event.map();
		final String simipandport=body.get("sim-ip-and-port").toString();
		for (@Nonnull final Circuit c: bot.getCircuits()) {
			if (c.getSimIPAndPort().equalsIgnoreCase(simipandport)) {
				if (Debug.EVENTQUEUE) { log.fine("Matched ip and port to circuit for region "+c.getRegionName()); }
				final String seedcaps=body.get("seed-capability").toString();
				c.connectCAPS(seedcaps);
				return;
			}
		}
		log.severe("Did not find simipandport "+simipandport+" to bind the event queue to");
	}

	@Nonnull
	@CmdHelp(description="Request the bot shutdown")
	public String quitCommand(final CommandEvent command) {
		bot.shutdown("Instant Message instructed us to quit");
		return "Shutting down as requested (this message will not be delivered)";
	}

	@Nonnull
	@CmdHelp(description="Causes the bot to reconnect to SL without quitting")
	public String restartCommand(final CommandEvent command) {
		bot.forceReconnect();
		log.warning("Restart command initiated");
		return "This IM reply probably will be lost due to the restart.";
	}

	@Nonnull
	@CmdHelp(description="Send an instant message")
	public String imCommand(final CommandEvent command,
	                        @Param(name="uuid",description="UUID to message") final String uuid,
	                        @Nonnull @Param(name="message",description="Message to send") final String message) {
		bot.im(new LLUUID(uuid),message);
		log.info("Sent IM to <"+uuid+"> "+bot.getUserName(new LLUUID(uuid))+" - "+message);
		return "IM sent";
	}

	@Nonnull
	@CmdHelp(description="Get Help :)  If you see this you're doing it right")
	public String helpCommand(final CommandEvent commandevent,
	                          @Nullable @Param(name="command",description="Optional command to get more info about") String command) {
		if (command==null || command.isEmpty()) {
			@Nonnull StringBuilder response=new StringBuilder();
			@Nonnull final Set<String> unsortedcommands=bot.brain().getCommands();
			@Nonnull final List<String> commands=new ArrayList<>(unsortedcommands);
			Collections.sort(commands);
			for (String acommand: commands) {
				if (response.length()>0) { response.append(", "); }
				else { response=new StringBuilder("\n"); }
				acommand=acommand.substring(0,acommand.length()-"command".length());
				response.append(acommand);
			}
			response.append("\n\nUse 'help command <command>' for more information");
			return response.toString();
		}
		command=command.toLowerCase();
		final Method m=bot.brain().getCommand(command);
		if (m==null) { throw new IllegalArgumentException("Could not find command"); }
		@Nullable final StringBuilder ret=new StringBuilder("\nCommand: "+command);
		if (m.getAnnotation(CmdHelp.class)!=null) {
			ret.append("\n").append(m.getAnnotation(CmdHelp.class).description());
		}
		for (@Nonnull final Parameter param: m.getParameters()) {
			if (!(param.getType().equals(Regional.class) || param.getType().equals(CommandEvent.class))) {
				final Param annotation = param.getAnnotation(Param.class);
				if (param.getAnnotation(Param.class)!=null) {
					ret.append("\n").append(annotation.name());
					ret.append(" - ").append(param.getAnnotation(Param.class).description());
				} else {
					ret.append("\nThis command is not properly documented (Missing annotations in ")
							.append(m.getDeclaringClass().getSimpleName())
							.append(".")
							.append(m.getName())
							.append(")"); }
			}
		}
		return ret.toString();
	}

	@Nonnull
	@CmdHelp(description="Pause the agent")
	public String pauseCommand(final CommandEvent event) {
		@Nonnull final AgentPause p=new AgentPause(bot);
		p.bagentdata.vserialnum=new U32(pauseserial++);
		bot.send(p,true);
		return "0 - Paused";
	}

	@Nonnull
	@CmdHelp(description="Unpause the agent")
	public String unPauseCommand(final CommandEvent event) {
		@Nonnull final AgentResume p=new AgentResume(bot);
		p.bagentdata.vserialnum=new U32(pauseserial++);
		bot.send(p,true);
		return "0 - Paused";
	}

	@Nonnull
	@CmdHelp(description="Say a message in local chat")
	public String sayCommand(final CommandEvent event,
	                         @Param(name="message",description = "Message to say")
	                         @Nonnull final String message) { return chat(1,message); }

	@Nonnull
	@CmdHelp(description="Shout a message in local chat")
	public String shoutCommand(final CommandEvent event,
							   @Param(name="message",description = "Message to shout")
	                           @Nonnull final String message) { return chat(2,message); }

	@Nonnull
	@CmdHelp(description="Whisper a message in local chat")
	public String whisperCommand(final CommandEvent event,
								 @Param(name="message",description = "Message to whisper")
	                             @Nonnull final String message) { return chat(0,message); }

	@Override
	public void maintenance() {
		if (bot.homeSickFor()==null) { return; } // we dont get homesick
		if (bot.getRegionName()==null || bot.getRegionName().isEmpty()) { return; } // we're nowhere (early login phase)
		if (homesickness!=null && homesickness.after(new Date())) { return; } // if timer set, wait for it to pass
		if (bot.getRegionName().equalsIgnoreCase(bot.homeSickFor())) { // if at home
			if (homesickness!=null) {
				homesickness=null;
				log.info("No longer homesick");
			}
			return;
		}
		// we are not at home oO
		// if our date thing is null this is the first time we've noticed ; set first TP home at now+60
		if (homesickness==null) {
			log.info("Bot not at home, beginning to feel home sick");
			homesickness=new Date(new Date().getTime()+60L*1000L);
			return;
		}
		// otherwise, go home
		log.info("Bot has homesickness and is attempting to teleport home during free-will"); // specifically when the brain isn't occupied, otherwise we wouldn't be in
		// maintenance
		new CommandEvent(bot,bot.getRegional(),"home",new HashMap<>(),null).execute();
		try { Thread.sleep(1000L); } catch (@Nonnull final InterruptedException ignored) {}
		if (bot.getRegionName().equalsIgnoreCase(bot.homeSickFor())) {
			// success
			log.info("Bot has successfully cured its self of homesickness by going home, bot is no longer homesick");
			homesickness=null;
			return;
		}
		// failure, retry in 5
		log.info("Bot attempted to return home but had no luck, skulking for 5 minutes, bot remains homesick");
		homesickness=new Date(new Date().getTime()+5L*60L*1000L);
	}

	@Nonnull
	@CmdHelp(description="Manage the homesickness of this bot")
	public String homesickCommand(final CommandEvent event,
	                              @Nullable @Param(name="region",description="Name of region to long for, blank to get current, or NONE to clear") final String region) {
		if (region==null || region.isEmpty()) {
			@Nullable final String home=bot.homeSickFor();
			if (home==null || home.isEmpty()) { return "Bot has no longing for any home"; }
			return "Bot longs for its home of '"+bot.homeSickFor()+"'";
		}
		if ("NONE".equals(region)) {
			bot.homeSickFor(null);
			config.put("homesickfor","");
			return "Bot longing for home cleared";
		}
		bot.homeSickFor(region);
		config.put("homesickfor",region);
		return "Bot now longs for home of '"+bot.homeSickFor()+"'";
	}

	@Nonnull
	@CmdHelp(description="Set the secret key")
	public String setSecretKeyCommand(final CommandEvent command,
							@Nonnull @Param(name="secretkey",description="Secret key to use (make it secure!)") final String secretKey) {
		bot.getConfig().put("secretkey",secretKey);
		if (secretKey.equalsIgnoreCase("password")) { return "No! Bad Human!"; }
		return bot.getConfig().persistent()?"Secret key changed and saved":"Secret key changed ; note your configuration store "+bot.getConfig().getClass().getSimpleName()+" is not persistent and changes will be lost on restart.";
	}

	/*
	@Nonnull @CmdHelp(description="Test command")
	public String testCommand(final CommandEvent command) throws IOException {
		bot.completeAgentMovement();
		bot.agentUpdate(true);
		//bot.getCAPS().dump();
		final LLSDMap req=new LLSDMap();
		req.put("cof_version",new LLSDInteger(132));
		final LLSD llsd=new LLSD(req);
		final LLSDMap response=bot.getCAPS().invokeCAPS("UpdateAvatarAppearance","",llsd);
		return "Test command executed "+response.toXML();
	}
	*/

	boolean hasCoffed;
	public void avatarAppearanceUDPDelayed(@Nonnull final UDPEvent event) {
		if (hasCoffed) { return; }
		@Nonnull final AvatarAppearance app = (AvatarAppearance) (event.body());
		if (app.bappearancedata.size()==0) {
			//System.out.println("Skipping no COF listed");
			return;
		}
		final int cofVersion = app.bappearancedata.get(0).vcofversion.value;
		if (cofVersion==0) {
			//System.out.println("Skip COF 0");
			return;
		}
		//System.out.println("REQUESTING COF "+cofVersion);
		@Nonnull final LLSDMap req=new LLSDMap();
		req.put("cof_version",new LLSDInteger(cofVersion));
		@Nonnull LLSD llsd=new LLSD(req);
		try {
			@Nullable final LLSDMap response = bot.getCAPS().invokeCAPS("UpdateAvatarAppearance", "", llsd);
		} catch (final IOException e) {
			final String error = e.getMessage();
			@Nonnull final Matcher matcher = Pattern.compile("Cof Version Mismatch: [0-9]+ != ([0-9]+)").matcher(error);
			if (matcher.matches()) {
				//System.out.println("Retrying for COF "+matcher.group(1));
				req.put("cof_version", new LLSDInteger(Integer.parseInt(matcher.group(1))));
				llsd = new LLSD(req);
				try {
					@Nullable final LLSDMap response = bot.getCAPS().invokeCAPS("UpdateAvatarAppearance", "", llsd);
				} catch (final IOException f) {
					log.log(WARNING, "Failed to fetch Appearance COF again " + matcher.group(1) + " - " + f, f);
					return;
				}
				hasCoffed = true;
				return;
			} else { log.log(WARNING,"Cof retrier failed to match error message:"+error); }
			log.log(WARNING,"Failed to fetch Appearance COF "+cofVersion+" - "+ e,e);
			return;
		}
		hasCoffed=true;
	}



	// ----- Internal Instance -----
	private String parseCommand(@Nonnull final String message,
	                            @Nonnull final Map<String,String> paramsout1) {
		@Nonnull final String[] parts=message.split(" ");
		int index=0;
		final String command=parts[0];
		index++;
		String keyword="";
		String parameter="";
		for (int i=index;i<parts.length;i++) {
			if ("".equals(keyword)) {
				keyword=parts[i];
			}
			else {
				if (!"".equals(parameter)) { parameter+=" "; }
				parameter+=parts[i];
				if ((!parameter.startsWith("\"")) || (parameter.startsWith("\"") && parameter.endsWith("\""))) {
					if (parameter.startsWith("\"")) {
						parameter=parameter.substring(1,parameter.length()-1);
					}
					paramsout1.put(keyword,parameter);
					keyword="";
					parameter="";
				}
			}
		}
		return command;
	}

	public void runCommands(final String from,
	                         @Nullable final LLUUID source,
	                         @Nonnull String message,
	                         @Nullable final String prefix,
							 final boolean bypassAuth) {
		boolean prefixok=false;
		if (prefix==null || prefix.isEmpty()) { prefixok=true; }
		if (!prefixok) {
			if (message.startsWith(prefix)) {
				message=message.substring(prefix.length());
				prefixok=true;
			}
		}
		if (!prefixok) { return; }
		log.info((source==null?"?":source.toUUIDString())+" <"+from+"> invokes command "+message);
		@Nonnull final Map<String,String> params=new HashMap<>();
		final String keyword=parseCommand(message,params);
		@Nullable String response;
		try {
			@Nullable final CommandEvent command=new CommandEvent(bot,bot.getRegional(),keyword,params,source);
			command.invokerUsername(from);
			command.invokerUUID(source);
			if (bypassAuth) { response=null; } else { response=bot.brain().auth(command); }
			if (response==null) { response=command.execute(); }
			else { log.warning("Caller "+from+" failed auth check : "+response); }
		}
		catch (@Nonnull final Exception e) {
			log.log(WARNING,"CnC Subcommand exceptioned:"+e,e);
			response="Exception:"+e;
		}
		if (!"".equals(response)) {
			if (source==null) {
				System.out.println(">> " + response);
			} else {
				if (bot.quitting()) {
					log.warning("Not sending IM response due to shutdown: " + response);
				} else {
					bot.im(source, ">> " + response);
				}
			}
		}
	}

	@Nonnull
	private String chat(final int messagetype,
	                    @Nonnull final String message) { return chat(messagetype,0,message); }

	@Nonnull
	private String chat(final int messagetype,
	                    final int channel,
	                    @Nonnull final String message) {
		@Nonnull final ChatFromViewer req=new ChatFromViewer(bot);
		req.bchatdata.vtype=new U8(messagetype);
		req.bchatdata.vchannel=new S32(channel);
		req.bchatdata.vmessage=new Variable2(message);
		bot.send(req,true);
		return "0 - Sent";
	}

	class CircuitLauncher extends Thread {
		final String numericip;
		final int port;
		final long handle;

		private CircuitLauncher(final JSLBot bot,
		                        final String numericip,
		                        final int port,
		                        final long handle) {
			this.numericip=numericip;
			this.port=port;
			this.handle=handle;
		}

		// ---------- INSTANCE ----------
		public void run() {
			try {
				bot.createCircuit(numericip,port,handle,null);
			}
			catch (@Nonnull final Exception e) {
				log.severe("Failed to set up circuit to "+Global.regionName(handle)+" (#"+Long.toUnsignedString(handle)+")");
			}

		}
	}

}
