package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ImageNotInDatabase extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 86; }
	@Nonnull
    public final String getName() { return "ImageNotInDatabase"; }
	@Nonnull
    @Sequence(0)
	public ImageNotInDatabase_bImageID bimageid=new ImageNotInDatabase_bImageID();
}
