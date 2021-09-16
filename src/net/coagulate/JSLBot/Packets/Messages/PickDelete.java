package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class PickDelete extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 186; }
	@Nonnull
    public final String getName() { return "PickDelete"; }
	@Nonnull
    @Sequence(0)
	public PickDelete_bAgentData bagentdata=new PickDelete_bAgentData();
	@Nonnull
    @Sequence(1)
	public PickDelete_bData bdata=new PickDelete_bData();
	public PickDelete(){}
	public PickDelete(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
