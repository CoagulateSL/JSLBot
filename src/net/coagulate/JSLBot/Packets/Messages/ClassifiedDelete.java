package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ClassifiedDelete extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 46; }
	@Nonnull
    public final String getName() { return "ClassifiedDelete"; }
	@Nonnull
    @Sequence(0)
	public ClassifiedDelete_bAgentData bagentdata=new ClassifiedDelete_bAgentData();
	@Nonnull
    @Sequence(1)
	public ClassifiedDelete_bData bdata=new ClassifiedDelete_bData();
	public ClassifiedDelete(){}
	public ClassifiedDelete(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
