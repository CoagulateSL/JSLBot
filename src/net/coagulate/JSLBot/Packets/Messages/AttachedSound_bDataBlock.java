package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.F32;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U8;

import javax.annotation.Nonnull;

public class AttachedSound_bDataBlock extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vsoundid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vobjectid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public LLUUID vownerid=new LLUUID();
	@Nonnull
    @Sequence(3)
	public F32 vgain=new F32();
	@Nonnull
    @Sequence(4)
	public U8 vflags=new U8();
}
