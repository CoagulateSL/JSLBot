package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U16;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.U8;

import javax.annotation.Nonnull;

public class ImageData_bImageID extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public U8 vcodec=new U8();
	@Nonnull
    @Sequence(2)
	public U32 vsize=new U32();
	@Nonnull
    @Sequence(3)
	public U16 vpackets=new U16();
}
