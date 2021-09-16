package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ClassifiedInfoUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 45; }
	@Nonnull
    public final String getName() { return "ClassifiedInfoUpdate"; }
	@Nonnull
    @Sequence(0)
	public ClassifiedInfoUpdate_bAgentData bagentdata=new ClassifiedInfoUpdate_bAgentData();
	@Nonnull
    @Sequence(1)
	public ClassifiedInfoUpdate_bData bdata=new ClassifiedInfoUpdate_bData();
	public ClassifiedInfoUpdate(){}
	public ClassifiedInfoUpdate(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
