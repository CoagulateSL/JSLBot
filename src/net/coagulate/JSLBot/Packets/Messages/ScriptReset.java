package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ScriptReset extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 246; }
	public final String getName() { return "ScriptReset"; }
	@Sequence(0)
	public ScriptReset_bAgentData bagentdata=new ScriptReset_bAgentData();
	@Sequence(1)
	public ScriptReset_bScript bscript=new ScriptReset_bScript();
	public ScriptReset(){}
	public ScriptReset(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
