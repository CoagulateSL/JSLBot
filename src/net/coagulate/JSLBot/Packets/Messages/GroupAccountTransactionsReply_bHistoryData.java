package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.S32;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class GroupAccountTransactionsReply_bHistoryData extends Block {
	@Nonnull
    @Sequence(0)
	public Variable1 vtime=new Variable1();
	@Nonnull
    @Sequence(1)
	public Variable1 vuser=new Variable1();
	@Nonnull
    @Sequence(2)
	public S32 vtype=new S32();
	@Nonnull
    @Sequence(3)
	public Variable1 vitem=new Variable1();
	@Nonnull
    @Sequence(4)
	public S32 vamount=new S32();
}
