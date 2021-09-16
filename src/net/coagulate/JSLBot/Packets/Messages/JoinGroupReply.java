package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class JoinGroupReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 344; }
	@Nonnull
    public final String getName() { return "JoinGroupReply"; }
	@Nonnull
    @Sequence(0)
	public JoinGroupReply_bAgentData bagentdata=new JoinGroupReply_bAgentData();
	@Nonnull
    @Sequence(1)
	public JoinGroupReply_bGroupData bgroupdata=new JoinGroupReply_bGroupData();
	public JoinGroupReply(){}
	public JoinGroupReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
