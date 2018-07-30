package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelSelectObjects extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 202; }
	public final String getName() { return "ParcelSelectObjects"; }
	@Sequence(0)
	public ParcelSelectObjects_bAgentData bagentdata=new ParcelSelectObjects_bAgentData();
	@Sequence(1)
	public ParcelSelectObjects_bParcelData bparceldata=new ParcelSelectObjects_bParcelData();
	@Sequence(2)
	public List<ParcelSelectObjects_bReturnIDs> breturnids;
}
