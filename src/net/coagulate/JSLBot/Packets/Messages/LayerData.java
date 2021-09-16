package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class LayerData extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 11; }
	@Nonnull
    public final String getName() { return "LayerData"; }
	@Nonnull
    @Sequence(0)
	public LayerData_bLayerID blayerid=new LayerData_bLayerID();
	@Nonnull
    @Sequence(1)
	public LayerData_bLayerData blayerdata=new LayerData_bLayerData();
}
