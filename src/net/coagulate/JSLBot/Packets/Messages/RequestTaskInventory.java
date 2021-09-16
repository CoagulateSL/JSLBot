package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class RequestTaskInventory extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 289; }
	@Nonnull
    public final String getName() { return "RequestTaskInventory"; }
	@Nonnull
    @Sequence(0)
	public RequestTaskInventory_bAgentData bagentdata=new RequestTaskInventory_bAgentData();
	@Nonnull
    @Sequence(1)
	public RequestTaskInventory_bInventoryData binventorydata=new RequestTaskInventory_bInventoryData();
	public RequestTaskInventory(){}
	public RequestTaskInventory(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
