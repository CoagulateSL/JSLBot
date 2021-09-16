package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class LinkInventoryItem extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 426; }
	@Nonnull
    public final String getName() { return "LinkInventoryItem"; }
	@Nonnull
    @Sequence(0)
	public LinkInventoryItem_bAgentData bagentdata=new LinkInventoryItem_bAgentData();
	@Nonnull
    @Sequence(1)
	public LinkInventoryItem_bInventoryBlock binventoryblock=new LinkInventoryItem_bInventoryBlock();
	public LinkInventoryItem(){}
	public LinkInventoryItem(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
