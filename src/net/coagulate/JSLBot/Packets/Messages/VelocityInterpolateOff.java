package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class VelocityInterpolateOff extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 126; }
	public final String getName() { return "VelocityInterpolateOff"; }
	@Sequence(0)
	public VelocityInterpolateOff_bAgentData bagentdata=new VelocityInterpolateOff_bAgentData();
	public VelocityInterpolateOff(){}
	public VelocityInterpolateOff(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
