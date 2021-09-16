package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.S32;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class MoneyBalanceReply_bTransactionInfo extends Block {
	@Nonnull
    @Sequence(0)
	public S32 vtransactiontype=new S32();
	@Nonnull
    @Sequence(1)
	public LLUUID vsourceid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public BOOL vissourcegroup=new BOOL();
	@Nonnull
    @Sequence(3)
	public LLUUID vdestid=new LLUUID();
	@Nonnull
    @Sequence(4)
	public BOOL visdestgroup=new BOOL();
	@Nonnull
    @Sequence(5)
	public S32 vamount=new S32();
	@Nonnull
    @Sequence(6)
	public Variable1 vitemdescription=new Variable1();
}
