package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class PurgeInventoryDescendents extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 285; }
	@Nonnull
    public final String getName() { return "PurgeInventoryDescendents"; }
	@Nonnull
    @Sequence(0)
	public PurgeInventoryDescendents_bAgentData bagentdata=new PurgeInventoryDescendents_bAgentData();
	@Nonnull
    @Sequence(1)
	public PurgeInventoryDescendents_bInventoryData binventorydata=new PurgeInventoryDescendents_bInventoryData();
	public PurgeInventoryDescendents(){}
	public PurgeInventoryDescendents(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
