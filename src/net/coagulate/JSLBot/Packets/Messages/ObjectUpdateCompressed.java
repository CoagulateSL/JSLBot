package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectUpdateCompressed extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 13; }
	@Nonnull
    public final String getName() { return "ObjectUpdateCompressed"; }
	@Nonnull
    @Sequence(0)
	public ObjectUpdateCompressed_bRegionData bregiondata=new ObjectUpdateCompressed_bRegionData();
	@Sequence(1)
	public List<ObjectUpdateCompressed_bObjectData> bobjectdata;
}
