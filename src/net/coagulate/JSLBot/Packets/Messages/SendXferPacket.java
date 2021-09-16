package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class SendXferPacket extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 18; }
	@Nonnull
    public final String getName() { return "SendXferPacket"; }
	@Nonnull
    @Sequence(0)
	public SendXferPacket_bXferID bxferid=new SendXferPacket_bXferID();
	@Nonnull
    @Sequence(1)
	public SendXferPacket_bDataPacket bdatapacket=new SendXferPacket_bDataPacket();
}
