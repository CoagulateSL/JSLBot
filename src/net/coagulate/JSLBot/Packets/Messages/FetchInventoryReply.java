package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class FetchInventoryReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 280; }
	public final String getName() { return "FetchInventoryReply"; }
	@Sequence(0)
	public FetchInventoryReply_bAgentData bagentdata=new FetchInventoryReply_bAgentData();
	@Sequence(1)
	public List<FetchInventoryReply_bInventoryData> binventorydata;
	public FetchInventoryReply(){}
	public FetchInventoryReply(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
