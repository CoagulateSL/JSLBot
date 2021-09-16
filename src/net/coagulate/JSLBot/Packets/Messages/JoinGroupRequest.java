package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class JoinGroupRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 343; }
	@Nonnull
    public final String getName() { return "JoinGroupRequest"; }
	@Nonnull
    @Sequence(0)
	public JoinGroupRequest_bAgentData bagentdata=new JoinGroupRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public JoinGroupRequest_bGroupData bgroupdata=new JoinGroupRequest_bGroupData();
	public JoinGroupRequest(){}
	public JoinGroupRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
