package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class PlacesQuery extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 29; }
	public final String getName() { return "PlacesQuery"; }
	@Sequence(0)
	public PlacesQuery_bAgentData bagentdata=new PlacesQuery_bAgentData();
	@Sequence(1)
	public PlacesQuery_bTransactionData btransactiondata=new PlacesQuery_bTransactionData();
	@Sequence(2)
	public PlacesQuery_bQueryData bquerydata=new PlacesQuery_bQueryData();
	public PlacesQuery(){}
	public PlacesQuery(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
