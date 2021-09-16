package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class FindAgent extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 256; }
	@Nonnull
    public final String getName() { return "FindAgent"; }
	@Nonnull
    @Sequence(0)
	public FindAgent_bAgentBlock bagentblock=new FindAgent_bAgentBlock();
	@Sequence(1)
	public List<FindAgent_bLocationBlock> blocationblock;
}
