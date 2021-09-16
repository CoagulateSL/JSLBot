package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.U8;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class RezSingleAttachmentFromInv_bObjectData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vitemid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vownerid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public U8 vattachmentpt=new U8();
	@Nonnull
    @Sequence(3)
	public U32 vitemflags=new U32();
	@Nonnull
    @Sequence(4)
	public U32 vgroupmask=new U32();
	@Nonnull
    @Sequence(5)
	public U32 veveryonemask=new U32();
	@Nonnull
    @Sequence(6)
	public U32 vnextownermask=new U32();
	@Nonnull
    @Sequence(7)
	public Variable1 vname=new Variable1();
	@Nonnull
    @Sequence(8)
	public Variable1 vdescription=new Variable1();
}
