package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 12; }
	@Nonnull
    public final String getName() { return "ObjectUpdate"; }
	@Nonnull
    @Sequence(0)
	public ObjectUpdate_bRegionData bregiondata=new ObjectUpdate_bRegionData();
	@Sequence(1)
	public List<ObjectUpdate_bObjectData> bobjectdata;
}
