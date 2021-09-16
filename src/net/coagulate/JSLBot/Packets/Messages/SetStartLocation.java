package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class SetStartLocation extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 325; }
	@Nonnull
    public final String getName() { return "SetStartLocation"; }
	@Nonnull
    @Sequence(0)
	public SetStartLocation_bStartLocationData bstartlocationdata=new SetStartLocation_bStartLocationData();
}
