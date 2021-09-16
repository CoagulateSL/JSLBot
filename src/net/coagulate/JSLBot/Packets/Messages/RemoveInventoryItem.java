package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class RemoveInventoryItem extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 270; }
	@Nonnull
    public final String getName() { return "RemoveInventoryItem"; }
	@Nonnull
    @Sequence(0)
	public RemoveInventoryItem_bAgentData bagentdata=new RemoveInventoryItem_bAgentData();
	@Sequence(1)
	public List<RemoveInventoryItem_bInventoryData> binventorydata;
	public RemoveInventoryItem(){}
	public RemoveInventoryItem(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
