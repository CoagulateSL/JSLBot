package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class GroupProfileRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 351; }
	@Nonnull
    public final String getName() { return "GroupProfileRequest"; }
	@Nonnull
    @Sequence(0)
	public GroupProfileRequest_bAgentData bagentdata=new GroupProfileRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public GroupProfileRequest_bGroupData bgroupdata=new GroupProfileRequest_bGroupData();
	public GroupProfileRequest(){}
	public GroupProfileRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
