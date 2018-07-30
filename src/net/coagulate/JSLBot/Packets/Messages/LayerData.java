package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class LayerData extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 11; }
	public final String getName() { return "LayerData"; }
	@Sequence(0)
	public LayerData_bLayerID blayerid=new LayerData_bLayerID();
	@Sequence(1)
	public LayerData_bLayerData blayerdata=new LayerData_bLayerData();
}
