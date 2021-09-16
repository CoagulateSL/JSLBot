package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class PlacesReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 30; }
	@Nonnull
    public final String getName() { return "PlacesReply"; }
	@Nonnull
    @Sequence(0)
	public PlacesReply_bAgentData bagentdata=new PlacesReply_bAgentData();
	@Nonnull
    @Sequence(1)
	public PlacesReply_bTransactionData btransactiondata=new PlacesReply_bTransactionData();
	@Sequence(2)
	public List<PlacesReply_bQueryData> bquerydata;
	public PlacesReply(){}
	public PlacesReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
