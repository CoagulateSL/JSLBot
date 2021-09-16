package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ClassifiedInfoRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 43; }
	@Nonnull
    public final String getName() { return "ClassifiedInfoRequest"; }
	@Nonnull
    @Sequence(0)
	public ClassifiedInfoRequest_bAgentData bagentdata=new ClassifiedInfoRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public ClassifiedInfoRequest_bData bdata=new ClassifiedInfoRequest_bData();
	public ClassifiedInfoRequest(){}
	public ClassifiedInfoRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
