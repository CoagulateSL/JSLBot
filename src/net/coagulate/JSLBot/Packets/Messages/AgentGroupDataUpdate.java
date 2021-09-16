package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class AgentGroupDataUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 389; }
	@Nonnull
    public final String getName() { return "AgentGroupDataUpdate"; }
	@Nonnull
    @Sequence(0)
	public AgentGroupDataUpdate_bAgentData bagentdata=new AgentGroupDataUpdate_bAgentData();
	@Sequence(1)
	public List<AgentGroupDataUpdate_bGroupData> bgroupdata;
	public AgentGroupDataUpdate(){}
	public AgentGroupDataUpdate(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
