package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class DeclineCallingCard extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 303; }
	@Nonnull
    public final String getName() { return "DeclineCallingCard"; }
	@Nonnull
    @Sequence(0)
	public DeclineCallingCard_bAgentData bagentdata=new DeclineCallingCard_bAgentData();
	@Nonnull
    @Sequence(1)
	public DeclineCallingCard_bTransactionBlock btransactionblock=new DeclineCallingCard_bTransactionBlock();
	public DeclineCallingCard(){}
	public DeclineCallingCard(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
