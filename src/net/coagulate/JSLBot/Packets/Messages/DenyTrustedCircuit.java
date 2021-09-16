package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class DenyTrustedCircuit extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 393; }
	@Nonnull
    public final String getName() { return "DenyTrustedCircuit"; }
	@Nonnull
    @Sequence(0)
	public DenyTrustedCircuit_bDataBlock bdatablock=new DenyTrustedCircuit_bDataBlock();
}
