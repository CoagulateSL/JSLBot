package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.U64;

import javax.annotation.Nonnull;

public class SendXferPacket_bXferID extends Block {
	@Nonnull
    @Sequence(0)
	public U64 vid=new U64();
	@Nonnull
    @Sequence(1)
	public U32 vpacket=new U32();
}
