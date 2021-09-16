package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class AgentQuitCopy extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 85; }
	@Nonnull
    public final String getName() { return "AgentQuitCopy"; }
	@Nonnull
    @Sequence(0)
	public AgentQuitCopy_bAgentData bagentdata=new AgentQuitCopy_bAgentData();
	@Nonnull
    @Sequence(1)
	public AgentQuitCopy_bFuseBlock bfuseblock=new AgentQuitCopy_bFuseBlock();
	public AgentQuitCopy(){}
	public AgentQuitCopy(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
