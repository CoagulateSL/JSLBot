package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ImagePacket extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 10; }
	@Nonnull
    public final String getName() { return "ImagePacket"; }
	@Nonnull
    @Sequence(0)
	public ImagePacket_bImageID bimageid=new ImagePacket_bImageID();
	@Nonnull
    @Sequence(1)
	public ImagePacket_bImageData bimagedata=new ImagePacket_bImageData();
}
