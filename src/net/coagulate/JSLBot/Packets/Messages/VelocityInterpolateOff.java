package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class VelocityInterpolateOff extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 126; }
	@Nonnull
    public final String getName() { return "VelocityInterpolateOff"; }
	@Nonnull
    @Sequence(0)
	public VelocityInterpolateOff_bAgentData bagentdata=new VelocityInterpolateOff_bAgentData();
	public VelocityInterpolateOff(){}
	public VelocityInterpolateOff(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
