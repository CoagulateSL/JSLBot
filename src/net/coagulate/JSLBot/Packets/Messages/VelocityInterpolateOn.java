package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class VelocityInterpolateOn extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 125; }
	@Nonnull
    public final String getName() { return "VelocityInterpolateOn"; }
	@Nonnull
    @Sequence(0)
	public VelocityInterpolateOn_bAgentData bagentdata=new VelocityInterpolateOn_bAgentData();
	public VelocityInterpolateOn(){}
	public VelocityInterpolateOn(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
