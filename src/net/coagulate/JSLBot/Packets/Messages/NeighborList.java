package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class NeighborList extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 3; }
	@Nonnull
    public final String getName() { return "NeighborList"; }
	@Nonnull
    @Sequence(0)
	public NeighborList_bNeighborBlock bneighborblock[]=new NeighborList_bNeighborBlock[4];
}
