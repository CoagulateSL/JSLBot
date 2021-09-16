package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class SoundTrigger extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 29; }
	@Nonnull
    public final String getName() { return "SoundTrigger"; }
	@Nonnull
    @Sequence(0)
	public SoundTrigger_bSoundData bsounddata=new SoundTrigger_bSoundData();
}
