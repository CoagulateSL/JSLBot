package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;

import javax.annotation.Nonnull;

public class EconomyDataRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 24; }
	@Nonnull
    public final String getName() { return "EconomyDataRequest"; }
}
