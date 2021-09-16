package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class GroupAccountDetailsRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 355; }
	@Nonnull
    public final String getName() { return "GroupAccountDetailsRequest"; }
	@Nonnull
    @Sequence(0)
	public GroupAccountDetailsRequest_bAgentData bagentdata=new GroupAccountDetailsRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public GroupAccountDetailsRequest_bMoneyData bmoneydata=new GroupAccountDetailsRequest_bMoneyData();
	public GroupAccountDetailsRequest(){}
	public GroupAccountDetailsRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
