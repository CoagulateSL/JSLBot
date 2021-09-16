package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.S32;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class MoneyBalanceReply_bMoneyData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vtransactionid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public BOOL vtransactionsuccess=new BOOL();
	@Nonnull
    @Sequence(3)
	public S32 vmoneybalance=new S32();
	@Nonnull
    @Sequence(4)
	public S32 vsquaremeterscredit=new S32();
	@Nonnull
    @Sequence(5)
	public S32 vsquaremeterscommitted=new S32();
	@Nonnull
    @Sequence(6)
	public Variable1 vdescription=new Variable1();
}
