package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ParcelMediaUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 420; }
	@Nonnull
    public final String getName() { return "ParcelMediaUpdate"; }
	@Nonnull
    @Sequence(0)
	public ParcelMediaUpdate_bDataBlock bdatablock=new ParcelMediaUpdate_bDataBlock();
	@Nonnull
    @Sequence(1)
	public ParcelMediaUpdate_bDataBlockExtended bdatablockextended=new ParcelMediaUpdate_bDataBlockExtended();
}
