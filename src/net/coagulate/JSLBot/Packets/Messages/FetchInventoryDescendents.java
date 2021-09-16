package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class FetchInventoryDescendents extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 277; }
	@Nonnull
    public final String getName() { return "FetchInventoryDescendents"; }
	@Nonnull
    @Sequence(0)
	public FetchInventoryDescendents_bAgentData bagentdata=new FetchInventoryDescendents_bAgentData();
	@Nonnull
    @Sequence(1)
	public FetchInventoryDescendents_bInventoryData binventorydata=new FetchInventoryDescendents_bInventoryData();
	public FetchInventoryDescendents(){}
	public FetchInventoryDescendents(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
