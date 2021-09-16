package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class SaveAssetIntoInventory extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 272; }
	@Nonnull
    public final String getName() { return "SaveAssetIntoInventory"; }
	@Nonnull
    @Sequence(0)
	public SaveAssetIntoInventory_bAgentData bagentdata=new SaveAssetIntoInventory_bAgentData();
	@Nonnull
    @Sequence(1)
	public SaveAssetIntoInventory_bInventoryData binventorydata=new SaveAssetIntoInventory_bInventoryData();
	public SaveAssetIntoInventory(){}
	public SaveAssetIntoInventory(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
