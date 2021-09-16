package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.S32;
import net.coagulate.JSLBot.Packets.Types.U8;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class MoneyTransferRequest_bMoneyData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vsourceid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vdestid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public U8 vflags=new U8();
	@Nonnull
    @Sequence(3)
	public S32 vamount=new S32();
	@Nonnull
    @Sequence(4)
	public U8 vaggregatepermnextowner=new U8();
	@Nonnull
    @Sequence(5)
	public U8 vaggregateperminventory=new U8();
	@Nonnull
    @Sequence(6)
	public S32 vtransactiontype=new S32();
	@Nonnull
    @Sequence(7)
	public Variable1 vdescription=new Variable1();
}
