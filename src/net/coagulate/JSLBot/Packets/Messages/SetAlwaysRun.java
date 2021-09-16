package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class SetAlwaysRun extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 88; }
	@Nonnull
    public final String getName() { return "SetAlwaysRun"; }
	@Nonnull
    @Sequence(0)
	public SetAlwaysRun_bAgentData bagentdata=new SetAlwaysRun_bAgentData();
	public SetAlwaysRun(){}
	public SetAlwaysRun(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
