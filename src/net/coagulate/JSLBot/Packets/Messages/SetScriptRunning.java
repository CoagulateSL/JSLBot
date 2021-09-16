package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class SetScriptRunning extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 245; }
	@Nonnull
    public final String getName() { return "SetScriptRunning"; }
	@Nonnull
    @Sequence(0)
	public SetScriptRunning_bAgentData bagentdata=new SetScriptRunning_bAgentData();
	@Nonnull
    @Sequence(1)
	public SetScriptRunning_bScript bscript=new SetScriptRunning_bScript();
	public SetScriptRunning(){}
	public SetScriptRunning(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
