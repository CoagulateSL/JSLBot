package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class DeclineFriendship extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 298; }
	@Nonnull
    public final String getName() { return "DeclineFriendship"; }
	@Nonnull
    @Sequence(0)
	public DeclineFriendship_bAgentData bagentdata=new DeclineFriendship_bAgentData();
	@Nonnull
    @Sequence(1)
	public DeclineFriendship_bTransactionBlock btransactionblock=new DeclineFriendship_bTransactionBlock();
	public DeclineFriendship(){}
	public DeclineFriendship(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
