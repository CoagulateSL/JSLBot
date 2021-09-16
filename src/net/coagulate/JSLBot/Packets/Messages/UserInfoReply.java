package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class UserInfoReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 400; }
	@Nonnull
    public final String getName() { return "UserInfoReply"; }
	@Nonnull
    @Sequence(0)
	public UserInfoReply_bAgentData bagentdata=new UserInfoReply_bAgentData();
	@Nonnull
    @Sequence(1)
	public UserInfoReply_bUserData buserdata=new UserInfoReply_bUserData();
	public UserInfoReply(){}
	public UserInfoReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
