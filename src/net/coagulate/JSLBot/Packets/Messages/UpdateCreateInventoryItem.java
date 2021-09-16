package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class UpdateCreateInventoryItem extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 267; }
	@Nonnull
    public final String getName() { return "UpdateCreateInventoryItem"; }
	@Nonnull
    @Sequence(0)
	public UpdateCreateInventoryItem_bAgentData bagentdata=new UpdateCreateInventoryItem_bAgentData();
	@Sequence(1)
	public List<UpdateCreateInventoryItem_bInventoryData> binventorydata;
	public UpdateCreateInventoryItem(){}
	public UpdateCreateInventoryItem(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
