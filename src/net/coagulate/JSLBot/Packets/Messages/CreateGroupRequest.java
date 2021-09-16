package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class CreateGroupRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 339; }
	@Nonnull
    public final String getName() { return "CreateGroupRequest"; }
	@Nonnull
    @Sequence(0)
	public CreateGroupRequest_bAgentData bagentdata=new CreateGroupRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public CreateGroupRequest_bGroupData bgroupdata=new CreateGroupRequest_bGroupData();
	public CreateGroupRequest(){}
	public CreateGroupRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
