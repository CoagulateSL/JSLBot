package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ScriptAnswerYes extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 132; }
	public final String getName() { return "ScriptAnswerYes"; }
	@Sequence(0)
	public ScriptAnswerYes_bAgentData bagentdata=new ScriptAnswerYes_bAgentData();
	@Sequence(1)
	public ScriptAnswerYes_bData bdata=new ScriptAnswerYes_bData();
	public ScriptAnswerYes(){}
	public ScriptAnswerYes(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
