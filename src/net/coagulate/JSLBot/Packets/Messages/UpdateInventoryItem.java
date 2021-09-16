package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class UpdateInventoryItem extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 266; }
	@Nonnull
    public final String getName() { return "UpdateInventoryItem"; }
	@Nonnull
    @Sequence(0)
	public UpdateInventoryItem_bAgentData bagentdata=new UpdateInventoryItem_bAgentData();
	@Sequence(1)
	public List<UpdateInventoryItem_bInventoryData> binventorydata;
	public UpdateInventoryItem(){}
	public UpdateInventoryItem(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
