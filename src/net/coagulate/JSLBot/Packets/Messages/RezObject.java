package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class RezObject extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 293; }
	@Nonnull
    public final String getName() { return "RezObject"; }
	@Nonnull
    @Sequence(0)
	public RezObject_bAgentData bagentdata=new RezObject_bAgentData();
	@Nonnull
    @Sequence(1)
	public RezObject_bRezData brezdata=new RezObject_bRezData();
	@Nonnull
    @Sequence(2)
	public RezObject_bInventoryData binventorydata=new RezObject_bInventoryData();
	public RezObject(){}
	public RezObject(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
