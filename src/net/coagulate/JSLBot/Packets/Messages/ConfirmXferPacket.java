package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ConfirmXferPacket extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 19; }
	@Nonnull
    public final String getName() { return "ConfirmXferPacket"; }
	@Nonnull
    @Sequence(0)
	public ConfirmXferPacket_bXferID bxferid=new ConfirmXferPacket_bXferID();
}
