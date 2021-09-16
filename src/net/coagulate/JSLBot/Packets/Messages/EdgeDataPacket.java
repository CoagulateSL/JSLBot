package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class EdgeDataPacket extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 24; }
	@Nonnull
    public final String getName() { return "EdgeDataPacket"; }
	@Nonnull
    @Sequence(0)
	public EdgeDataPacket_bEdgeData bedgedata=new EdgeDataPacket_bEdgeData();
}
