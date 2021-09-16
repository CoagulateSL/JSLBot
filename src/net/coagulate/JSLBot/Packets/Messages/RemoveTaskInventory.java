package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class RemoveTaskInventory extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 287; }
	@Nonnull
    public final String getName() { return "RemoveTaskInventory"; }
	@Nonnull
    @Sequence(0)
	public RemoveTaskInventory_bAgentData bagentdata=new RemoveTaskInventory_bAgentData();
	@Nonnull
    @Sequence(1)
	public RemoveTaskInventory_bInventoryData binventorydata=new RemoveTaskInventory_bInventoryData();
	public RemoveTaskInventory(){}
	public RemoveTaskInventory(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
