package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class OpenCircuit extends Block implements Message {
	public final int getFrequency() { return Frequency.FIXED; }
	public final int getId() { return 0xFFFFFFFC; }
	@Nonnull
    public final String getName() { return "OpenCircuit"; }
	@Nonnull
    @Sequence(0)
	public OpenCircuit_bCircuitInfo bcircuitinfo=new OpenCircuit_bCircuitInfo();
}
