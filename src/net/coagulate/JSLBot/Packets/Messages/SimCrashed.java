package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class SimCrashed extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 328; }
	@Nonnull
    public final String getName() { return "SimCrashed"; }
	@Nonnull
    @Sequence(0)
	public SimCrashed_bData bdata=new SimCrashed_bData();
	@Sequence(1)
	public List<SimCrashed_bUsers> busers;
}
