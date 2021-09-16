package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class AttachedSound extends Block implements Message {
	public final int getFrequency() { return Frequency.MEDIUM; }
	public final int getId() { return 13; }
	@Nonnull
    public final String getName() { return "AttachedSound"; }
	@Nonnull
    @Sequence(0)
	public AttachedSound_bDataBlock bdatablock=new AttachedSound_bDataBlock();
}
