package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ClassifiedInfoReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 44; }
	@Nonnull
    public final String getName() { return "ClassifiedInfoReply"; }
	@Nonnull
    @Sequence(0)
	public ClassifiedInfoReply_bAgentData bagentdata=new ClassifiedInfoReply_bAgentData();
	@Nonnull
    @Sequence(1)
	public ClassifiedInfoReply_bData bdata=new ClassifiedInfoReply_bData();
	public ClassifiedInfoReply(){}
	public ClassifiedInfoReply(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
