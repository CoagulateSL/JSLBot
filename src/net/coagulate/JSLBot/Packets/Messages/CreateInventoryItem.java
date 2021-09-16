package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class CreateInventoryItem extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 305; }
	@Nonnull
    public final String getName() { return "CreateInventoryItem"; }
	@Nonnull
    @Sequence(0)
	public CreateInventoryItem_bAgentData bagentdata=new CreateInventoryItem_bAgentData();
	@Nonnull
    @Sequence(1)
	public CreateInventoryItem_bInventoryBlock binventoryblock=new CreateInventoryItem_bInventoryBlock();
	public CreateInventoryItem(){}
	public CreateInventoryItem(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
