package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ImprovedTerseObjectUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 15; }
	@Nonnull
    public final String getName() { return "ImprovedTerseObjectUpdate"; }
	@Nonnull
    @Sequence(0)
	public ImprovedTerseObjectUpdate_bRegionData bregiondata=new ImprovedTerseObjectUpdate_bRegionData();
	@Sequence(1)
	public List<ImprovedTerseObjectUpdate_bObjectData> bobjectdata;
}
