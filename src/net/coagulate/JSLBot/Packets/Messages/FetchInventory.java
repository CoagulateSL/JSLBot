package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class FetchInventory extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 279; }
	@Nonnull
    public final String getName() { return "FetchInventory"; }
	@Nonnull
    @Sequence(0)
	public FetchInventory_bAgentData bagentdata=new FetchInventory_bAgentData();
	@Sequence(1)
	public List<FetchInventory_bInventoryData> binventorydata;
	public FetchInventory(){}
	public FetchInventory(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
