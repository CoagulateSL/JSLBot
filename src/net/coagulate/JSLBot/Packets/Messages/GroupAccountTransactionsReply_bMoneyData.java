package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.S32;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class GroupAccountTransactionsReply_bMoneyData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vrequestid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public S32 vintervaldays=new S32();
	@Nonnull
    @Sequence(2)
	public S32 vcurrentinterval=new S32();
	@Nonnull
    @Sequence(3)
	public Variable1 vstartdate=new Variable1();
}
