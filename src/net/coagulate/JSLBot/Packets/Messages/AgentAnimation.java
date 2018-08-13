package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentAnimation extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 5; }
	public final String getName() { return "AgentAnimation"; }
	@Sequence(0)
	public AgentAnimation_bAgentData bagentdata=new AgentAnimation_bAgentData();
	@Sequence(1)
	public List<AgentAnimation_bAnimationList> banimationlist;
	@Sequence(2)
	public List<AgentAnimation_bPhysicalAvatarEventList> bphysicalavatareventlist;
	public AgentAnimation(){}
	public AgentAnimation(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
