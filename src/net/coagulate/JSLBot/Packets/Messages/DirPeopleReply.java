package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class DirPeopleReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 36; }
	@Nonnull
    public final String getName() { return "DirPeopleReply"; }
	@Nonnull
    @Sequence(0)
	public DirPeopleReply_bAgentData bagentdata=new DirPeopleReply_bAgentData();
	@Nonnull
    @Sequence(1)
	public DirPeopleReply_bQueryData bquerydata=new DirPeopleReply_bQueryData();
	@Sequence(2)
	public List<DirPeopleReply_bQueryReplies> bqueryreplies;
	public DirPeopleReply(){}
	public DirPeopleReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
