package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class PlacesQuery extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 29; }
	@Nonnull
    public final String getName() { return "PlacesQuery"; }
	@Nonnull
    @Sequence(0)
	public PlacesQuery_bAgentData bagentdata=new PlacesQuery_bAgentData();
	@Nonnull
    @Sequence(1)
	public PlacesQuery_bTransactionData btransactiondata=new PlacesQuery_bTransactionData();
	@Nonnull
    @Sequence(2)
	public PlacesQuery_bQueryData bquerydata=new PlacesQuery_bQueryData();
	public PlacesQuery(){}
	public PlacesQuery(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
