package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class EjectGroupMemberRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 345; }
	@Nonnull
    public final String getName() { return "EjectGroupMemberRequest"; }
	@Nonnull
    @Sequence(0)
	public EjectGroupMemberRequest_bAgentData bagentdata=new EjectGroupMemberRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public EjectGroupMemberRequest_bGroupData bgroupdata=new EjectGroupMemberRequest_bGroupData();
	@Sequence(2)
	public List<EjectGroupMemberRequest_bEjectData> bejectdata;
	public EjectGroupMemberRequest(){}
	public EjectGroupMemberRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
