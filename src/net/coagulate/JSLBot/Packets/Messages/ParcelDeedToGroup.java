package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ParcelDeedToGroup extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 207; }
	@Nonnull
    public final String getName() { return "ParcelDeedToGroup"; }
	@Nonnull
    @Sequence(0)
	public ParcelDeedToGroup_bAgentData bagentdata=new ParcelDeedToGroup_bAgentData();
	@Nonnull
    @Sequence(1)
	public ParcelDeedToGroup_bData bdata=new ParcelDeedToGroup_bData();
	public ParcelDeedToGroup(){}
	public ParcelDeedToGroup(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
