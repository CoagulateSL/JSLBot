package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class SimStatus extends Block implements Message {
	public final int getFrequency() { return Frequency.MEDIUM; }
	public final int getId() { return 12; }
	@Nonnull
    public final String getName() { return "SimStatus"; }
	@Nonnull
    @Sequence(0)
	public SimStatus_bSimStatus bsimstatus=new SimStatus_bSimStatus();
	@Nonnull
    @Sequence(1)
	public SimStatus_bSimFlags bsimflags=new SimStatus_bSimFlags();
}
