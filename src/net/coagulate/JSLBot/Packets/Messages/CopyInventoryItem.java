package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class CopyInventoryItem extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 269; }
	@Nonnull
    public final String getName() { return "CopyInventoryItem"; }
	@Nonnull
    @Sequence(0)
	public CopyInventoryItem_bAgentData bagentdata=new CopyInventoryItem_bAgentData();
	@Sequence(1)
	public List<CopyInventoryItem_bInventoryData> binventorydata;
	public CopyInventoryItem(){}
	public CopyInventoryItem(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
