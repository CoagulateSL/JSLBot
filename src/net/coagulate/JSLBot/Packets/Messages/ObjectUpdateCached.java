package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectUpdateCached extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 14; }
	@Nonnull
    public final String getName() { return "ObjectUpdateCached"; }
	@Nonnull
    @Sequence(0)
	public ObjectUpdateCached_bRegionData bregiondata=new ObjectUpdateCached_bRegionData();
	@Sequence(1)
	public List<ObjectUpdateCached_bObjectData> bobjectdata;
}
