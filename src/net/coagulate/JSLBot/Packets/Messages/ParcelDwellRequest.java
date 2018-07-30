package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelDwellRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 218; }
	public final String getName() { return "ParcelDwellRequest"; }
	@Sequence(0)
	public ParcelDwellRequest_bAgentData bagentdata=new ParcelDwellRequest_bAgentData();
	@Sequence(1)
	public ParcelDwellRequest_bData bdata=new ParcelDwellRequest_bData();
}
