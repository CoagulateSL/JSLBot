package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class GroupActiveProposalItemReply_bProposalData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vvoteid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vvoteinitiator=new LLUUID();
	@Nonnull
    @Sequence(2)
	public Variable1 vtersedateid=new Variable1();
	@Nonnull
    @Sequence(3)
	public Variable1 vstartdatetime=new Variable1();
	@Nonnull
    @Sequence(4)
	public Variable1 venddatetime=new Variable1();
	@Nonnull
    @Sequence(5)
	public BOOL valreadyvoted=new BOOL();
	@Nonnull
    @Sequence(6)
	public Variable1 vvotecast=new Variable1();
	@Nonnull
    @Sequence(7)
	public F32 vmajority=new F32();
	@Nonnull
    @Sequence(8)
	public S32 vquorum=new S32();
	@Nonnull
    @Sequence(9)
	public Variable1 vproposaltext=new Variable1();
}
