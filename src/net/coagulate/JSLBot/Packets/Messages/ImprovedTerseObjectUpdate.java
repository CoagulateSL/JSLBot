package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ImprovedTerseObjectUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 15; }
	public final String getName() { return "ImprovedTerseObjectUpdate"; }
	@Sequence(0)
	public ImprovedTerseObjectUpdate_bRegionData bregiondata=new ImprovedTerseObjectUpdate_bRegionData();
	@Sequence(1)
	public List<ImprovedTerseObjectUpdate_bObjectData> bobjectdata;
}
