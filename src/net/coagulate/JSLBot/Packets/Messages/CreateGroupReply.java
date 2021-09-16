package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class CreateGroupReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 340; }
	@Nonnull
    public final String getName() { return "CreateGroupReply"; }
	@Nonnull
    @Sequence(0)
	public CreateGroupReply_bAgentData bagentdata=new CreateGroupReply_bAgentData();
	@Nonnull
    @Sequence(1)
	public CreateGroupReply_bReplyData breplydata=new CreateGroupReply_bReplyData();
	public CreateGroupReply(){}
	public CreateGroupReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
