package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U16;

import javax.annotation.Nonnull;

public class ImagePacket_bImageID extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public U16 vpacket=new U16();
}
