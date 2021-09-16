package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class CompletePingCheck extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 2; }
	@Nonnull
    public final String getName() { return "CompletePingCheck"; }
	@Nonnull
    @Sequence(0)
	public CompletePingCheck_bPingID bpingid=new CompletePingCheck_bPingID();
}
