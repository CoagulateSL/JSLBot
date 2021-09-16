package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class SetGroupContribution extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 369; }
	@Nonnull
    public final String getName() { return "SetGroupContribution"; }
	@Nonnull
    @Sequence(0)
	public SetGroupContribution_bAgentData bagentdata=new SetGroupContribution_bAgentData();
	@Nonnull
    @Sequence(1)
	public SetGroupContribution_bData bdata=new SetGroupContribution_bData();
	public SetGroupContribution(){}
	public SetGroupContribution(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
