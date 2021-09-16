package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ParcelAuctions extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 234; }
	@Nonnull
    public final String getName() { return "ParcelAuctions"; }
	@Sequence(0)
	public List<ParcelAuctions_bParcelData> bparceldata;
}
