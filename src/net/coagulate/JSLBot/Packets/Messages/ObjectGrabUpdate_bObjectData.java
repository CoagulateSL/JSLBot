package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.LLVector3;
import net.coagulate.JSLBot.Packets.Types.U32;

import javax.annotation.Nonnull;

public class ObjectGrabUpdate_bObjectData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vobjectid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLVector3 vgraboffsetinitial=new LLVector3();
	@Nonnull
    @Sequence(2)
	public LLVector3 vgrabposition=new LLVector3();
	@Nonnull
    @Sequence(3)
	public U32 vtimesincelast=new U32();
}
