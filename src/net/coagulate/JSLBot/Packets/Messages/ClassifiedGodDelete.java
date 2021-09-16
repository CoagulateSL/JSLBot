package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ClassifiedGodDelete extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 47; }
	@Nonnull
    public final String getName() { return "ClassifiedGodDelete"; }
	@Nonnull
    @Sequence(0)
	public ClassifiedGodDelete_bAgentData bagentdata=new ClassifiedGodDelete_bAgentData();
	@Nonnull
    @Sequence(1)
	public ClassifiedGodDelete_bData bdata=new ClassifiedGodDelete_bData();
	public ClassifiedGodDelete(){}
	public ClassifiedGodDelete(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
