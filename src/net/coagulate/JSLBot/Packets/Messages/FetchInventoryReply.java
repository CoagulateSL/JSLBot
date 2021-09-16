package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class FetchInventoryReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 280; }
	@Nonnull
    public final String getName() { return "FetchInventoryReply"; }
	@Nonnull
    @Sequence(0)
	public FetchInventoryReply_bAgentData bagentdata=new FetchInventoryReply_bAgentData();
	@Sequence(1)
	public List<FetchInventoryReply_bInventoryData> binventorydata;
	public FetchInventoryReply(){}
	public FetchInventoryReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
