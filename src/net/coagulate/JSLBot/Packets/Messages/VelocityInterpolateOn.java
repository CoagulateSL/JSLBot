package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class VelocityInterpolateOn extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 125; }
	public final String getName() { return "VelocityInterpolateOn"; }
	@Sequence(0)
	public VelocityInterpolateOn_bAgentData bagentdata=new VelocityInterpolateOn_bAgentData();
	public VelocityInterpolateOn(){}
	public VelocityInterpolateOn(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
