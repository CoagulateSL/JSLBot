package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class AttachedSoundGainChange extends Block implements Message {
	public final int getFrequency() { return Frequency.MEDIUM; }
	public final int getId() { return 14; }
	@Nonnull
    public final String getName() { return "AttachedSoundGainChange"; }
	@Nonnull
    @Sequence(0)
	public AttachedSoundGainChange_bDataBlock bdatablock=new AttachedSoundGainChange_bDataBlock();
}
