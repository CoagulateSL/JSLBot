package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class MoveInventoryItem extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 268; }
	@Nonnull
    public final String getName() { return "MoveInventoryItem"; }
	@Nonnull
    @Sequence(0)
	public MoveInventoryItem_bAgentData bagentdata=new MoveInventoryItem_bAgentData();
	@Sequence(1)
	public List<MoveInventoryItem_bInventoryData> binventorydata;
	public MoveInventoryItem(){}
	public MoveInventoryItem(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
