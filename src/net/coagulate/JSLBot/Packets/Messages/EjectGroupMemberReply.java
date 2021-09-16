package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class EjectGroupMemberReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 346; }
	@Nonnull
    public final String getName() { return "EjectGroupMemberReply"; }
	@Nonnull
    @Sequence(0)
	public EjectGroupMemberReply_bAgentData bagentdata=new EjectGroupMemberReply_bAgentData();
	@Nonnull
    @Sequence(1)
	public EjectGroupMemberReply_bGroupData bgroupdata=new EjectGroupMemberReply_bGroupData();
	@Nonnull
    @Sequence(2)
	public EjectGroupMemberReply_bEjectData bejectdata=new EjectGroupMemberReply_bEjectData();
	public EjectGroupMemberReply(){}
	public EjectGroupMemberReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
