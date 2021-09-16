package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class AddCircuitCode extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 2; }
	@Nonnull
    public final String getName() { return "AddCircuitCode"; }
	@Nonnull
    @Sequence(0)
	public AddCircuitCode_bCircuitCode bcircuitcode=new AddCircuitCode_bCircuitCode();
}
