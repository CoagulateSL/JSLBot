package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;

import javax.annotation.Nonnull;

public class CloseCircuit extends Block implements Message {
	public final int getFrequency() { return Frequency.FIXED; }
	public final int getId() { return 0xFFFFFFFD; }
	@Nonnull
    public final String getName() { return "CloseCircuit"; }
}
