package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class UpdateUserInfo extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 401; }
	@Nonnull
    public final String getName() { return "UpdateUserInfo"; }
	@Nonnull
    @Sequence(0)
	public UpdateUserInfo_bAgentData bagentdata=new UpdateUserInfo_bAgentData();
	@Nonnull
    @Sequence(1)
	public UpdateUserInfo_bUserData buserdata=new UpdateUserInfo_bUserData();
	public UpdateUserInfo(){}
	public UpdateUserInfo(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
