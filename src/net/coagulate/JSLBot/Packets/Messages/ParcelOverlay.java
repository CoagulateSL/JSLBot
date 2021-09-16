package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ParcelOverlay extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 196; }
	@Nonnull
    public final String getName() { return "ParcelOverlay"; }
	@Nonnull
    @Sequence(0)
	public ParcelOverlay_bParcelData bparceldata=new ParcelOverlay_bParcelData();
}
