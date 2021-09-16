package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class HealthMessage extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 138; }
	@Nonnull
    public final String getName() { return "HealthMessage"; }
	@Nonnull
    @Sequence(0)
	public HealthMessage_bHealthData bhealthdata=new HealthMessage_bHealthData();
}
