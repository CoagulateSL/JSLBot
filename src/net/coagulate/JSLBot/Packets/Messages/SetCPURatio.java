package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class SetCPURatio extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 327; }
	@Nonnull
    public final String getName() { return "SetCPURatio"; }
	@Nonnull
    @Sequence(0)
	public SetCPURatio_bData bdata=new SetCPURatio_bData();
}
