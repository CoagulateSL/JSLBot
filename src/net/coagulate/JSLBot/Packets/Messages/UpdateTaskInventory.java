package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class UpdateTaskInventory extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 286; }
	@Nonnull
    public final String getName() { return "UpdateTaskInventory"; }
	@Nonnull
    @Sequence(0)
	public UpdateTaskInventory_bAgentData bagentdata=new UpdateTaskInventory_bAgentData();
	@Nonnull
    @Sequence(1)
	public UpdateTaskInventory_bUpdateData bupdatedata=new UpdateTaskInventory_bUpdateData();
	@Nonnull
    @Sequence(2)
	public UpdateTaskInventory_bInventoryData binventorydata=new UpdateTaskInventory_bInventoryData();
	public UpdateTaskInventory(){}
	public UpdateTaskInventory(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
