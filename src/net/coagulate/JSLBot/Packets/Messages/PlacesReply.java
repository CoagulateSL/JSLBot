package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class PlacesReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 30; }
	public final String getName() { return "PlacesReply"; }
	@Sequence(0)
	public PlacesReply_bAgentData bagentdata=new PlacesReply_bAgentData();
	@Sequence(1)
	public PlacesReply_bTransactionData btransactiondata=new PlacesReply_bTransactionData();
	@Sequence(2)
	public List<PlacesReply_bQueryData> bquerydata;
}
