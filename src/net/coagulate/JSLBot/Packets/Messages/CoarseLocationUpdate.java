package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class CoarseLocationUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.MEDIUM; }
	public final int getId() { return 6; }
	@Nonnull
    public final String getName() { return "CoarseLocationUpdate"; }
	@Sequence(0)
	public List<CoarseLocationUpdate_bLocation> blocation;
	@Nonnull
    @Sequence(1)
	public CoarseLocationUpdate_bIndex bindex=new CoarseLocationUpdate_bIndex();
	@Sequence(2)
	public List<CoarseLocationUpdate_bAgentData> bagentdata;
}
