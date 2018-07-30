package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelAccessListUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 217; }
	public final String getName() { return "ParcelAccessListUpdate"; }
	@Sequence(0)
	public ParcelAccessListUpdate_bAgentData bagentdata=new ParcelAccessListUpdate_bAgentData();
	@Sequence(1)
	public ParcelAccessListUpdate_bData bdata=new ParcelAccessListUpdate_bData();
	@Sequence(2)
	public List<ParcelAccessListUpdate_bList> blist;
}
