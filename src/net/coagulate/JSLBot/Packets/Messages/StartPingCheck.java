package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class StartPingCheck extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 1; }
	@Nonnull
    public final String getName() { return "StartPingCheck"; }
	@Nonnull
    @Sequence(0)
	public StartPingCheck_bPingID bpingid=new StartPingCheck_bPingID();
}
