package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class UpdateGroupInfo extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 341; }
	@Nonnull
    public final String getName() { return "UpdateGroupInfo"; }
	@Nonnull
    @Sequence(0)
	public UpdateGroupInfo_bAgentData bagentdata=new UpdateGroupInfo_bAgentData();
	@Nonnull
    @Sequence(1)
	public UpdateGroupInfo_bGroupData bgroupdata=new UpdateGroupInfo_bGroupData();
	public UpdateGroupInfo(){}
	public UpdateGroupInfo(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
