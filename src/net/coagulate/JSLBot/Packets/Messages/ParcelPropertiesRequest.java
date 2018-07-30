package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelPropertiesRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.MEDIUM; }
	public final int getId() { return 11; }
	public final String getName() { return "ParcelPropertiesRequest"; }
	@Sequence(0)
	public ParcelPropertiesRequest_bAgentData bagentdata=new ParcelPropertiesRequest_bAgentData();
	@Sequence(1)
	public ParcelPropertiesRequest_bParcelData bparceldata=new ParcelPropertiesRequest_bParcelData();
}
