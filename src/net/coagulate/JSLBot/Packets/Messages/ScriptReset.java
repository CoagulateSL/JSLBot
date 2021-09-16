package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ScriptReset extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 246; }
	@Nonnull
    public final String getName() { return "ScriptReset"; }
	@Nonnull
    @Sequence(0)
	public ScriptReset_bAgentData bagentdata=new ScriptReset_bAgentData();
	@Nonnull
    @Sequence(1)
	public ScriptReset_bScript bscript=new ScriptReset_bScript();
	public ScriptReset(){}
	public ScriptReset(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
