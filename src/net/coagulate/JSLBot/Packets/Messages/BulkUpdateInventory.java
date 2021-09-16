package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class BulkUpdateInventory extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 281; }
	@Nonnull
    public final String getName() { return "BulkUpdateInventory"; }
	@Nonnull
    @Sequence(0)
	public BulkUpdateInventory_bAgentData bagentdata=new BulkUpdateInventory_bAgentData();
	@Sequence(1)
	public List<BulkUpdateInventory_bFolderData> bfolderdata;
	@Sequence(2)
	public List<BulkUpdateInventory_bItemData> bitemdata;
	public BulkUpdateInventory(){}
	public BulkUpdateInventory(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
