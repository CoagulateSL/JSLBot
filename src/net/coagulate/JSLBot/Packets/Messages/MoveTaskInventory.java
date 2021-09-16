package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class MoveTaskInventory extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 288; }
	@Nonnull
    public final String getName() { return "MoveTaskInventory"; }
	@Nonnull
    @Sequence(0)
	public MoveTaskInventory_bAgentData bagentdata=new MoveTaskInventory_bAgentData();
	@Nonnull
    @Sequence(1)
	public MoveTaskInventory_bInventoryData binventorydata=new MoveTaskInventory_bInventoryData();
	public MoveTaskInventory(){}
	public MoveTaskInventory(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
