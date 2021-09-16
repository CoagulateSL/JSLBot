package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class DerezContainer extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 104; }
	@Nonnull
    public final String getName() { return "DerezContainer"; }
	@Nonnull
    @Sequence(0)
	public DerezContainer_bData bdata=new DerezContainer_bData();
}
