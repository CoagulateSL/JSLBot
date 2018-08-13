package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ForceScriptControlRelease extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 192; }
	public final String getName() { return "ForceScriptControlRelease"; }
	@Sequence(0)
	public ForceScriptControlRelease_bAgentData bagentdata=new ForceScriptControlRelease_bAgentData();
	public ForceScriptControlRelease(){}
	public ForceScriptControlRelease(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
