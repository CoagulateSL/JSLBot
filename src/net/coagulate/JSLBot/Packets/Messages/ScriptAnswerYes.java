package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ScriptAnswerYes extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 132; }
	@Nonnull
    public final String getName() { return "ScriptAnswerYes"; }
	@Nonnull
    @Sequence(0)
	public ScriptAnswerYes_bAgentData bagentdata=new ScriptAnswerYes_bAgentData();
	@Nonnull
    @Sequence(1)
	public ScriptAnswerYes_bData bdata=new ScriptAnswerYes_bData();
	public ScriptAnswerYes(){}
	public ScriptAnswerYes(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
