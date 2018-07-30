package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelDisableObjects extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 201; }
	public final String getName() { return "ParcelDisableObjects"; }
	@Sequence(0)
	public ParcelDisableObjects_bAgentData bagentdata=new ParcelDisableObjects_bAgentData();
	@Sequence(1)
	public ParcelDisableObjects_bParcelData bparceldata=new ParcelDisableObjects_bParcelData();
	@Sequence(2)
	public List<ParcelDisableObjects_bTaskIDs> btaskids;
	@Sequence(3)
	public List<ParcelDisableObjects_bOwnerIDs> bownerids;
}
