package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class InventoryDescendents extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 278; }
	@Nonnull
    public final String getName() { return "InventoryDescendents"; }
	@Nonnull
    @Sequence(0)
	public InventoryDescendents_bAgentData bagentdata=new InventoryDescendents_bAgentData();
	@Sequence(1)
	public List<InventoryDescendents_bFolderData> bfolderdata;
	@Sequence(2)
	public List<InventoryDescendents_bItemData> bitemdata;
	public InventoryDescendents(){}
	public InventoryDescendents(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
