package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class CreateGroupRequestExtended_bGroupData extends Block {
	@Nonnull
    @Sequence(0)
	public Variable1 vname=new Variable1();
	@Nonnull
    @Sequence(1)
	public Variable2 vcharter=new Variable2();
	@Nonnull
    @Sequence(2)
	public BOOL vshowinlist=new BOOL();
	@Nonnull
    @Sequence(3)
	public LLUUID vinsigniaid=new LLUUID();
	@Nonnull
    @Sequence(4)
	public S32 vmembershipfee=new S32();
	@Nonnull
    @Sequence(5)
	public BOOL vopenenrollment=new BOOL();
	@Nonnull
    @Sequence(6)
	public BOOL vallowpublish=new BOOL();
	@Nonnull
    @Sequence(7)
	public BOOL vmaturepublish=new BOOL();
}
