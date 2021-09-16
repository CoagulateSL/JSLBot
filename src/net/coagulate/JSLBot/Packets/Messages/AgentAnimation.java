package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class AgentAnimation extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 5; }
	@Nonnull
    public final String getName() { return "AgentAnimation"; }
	@Nonnull
    @Sequence(0)
	public AgentAnimation_bAgentData bagentdata=new AgentAnimation_bAgentData();
	@Sequence(1)
	public List<AgentAnimation_bAnimationList> banimationlist;
	@Sequence(2)
	public List<AgentAnimation_bPhysicalAvatarEventList> bphysicalavatareventlist;
	public AgentAnimation(){}
	public AgentAnimation(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
