package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ImageData extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 9; }
	@Nonnull
    public final String getName() { return "ImageData"; }
	@Nonnull
    @Sequence(0)
	public ImageData_bImageID bimageid=new ImageData_bImageID();
	@Nonnull
    @Sequence(1)
	public ImageData_bImageData bimagedata=new ImageData_bImageData();
}
