package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class PickInfoUpdate_bData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vpickid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vcreatorid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public BOOL vtoppick=new BOOL();
	@Nonnull
    @Sequence(3)
	public LLUUID vparcelid=new LLUUID();
	@Nonnull
    @Sequence(4)
	public Variable1 vname=new Variable1();
	@Nonnull
    @Sequence(5)
	public Variable2 vdesc=new Variable2();
	@Nonnull
    @Sequence(6)
	public LLUUID vsnapshotid=new LLUUID();
	@Nonnull
    @Sequence(7)
	public LLVector3d vposglobal=new LLVector3d();
	@Nonnull
    @Sequence(8)
	public S32 vsortorder=new S32();
	@Nonnull
    @Sequence(9)
	public BOOL venabled=new BOOL();
}
