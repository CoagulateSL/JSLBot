package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class EjectGroupMemberReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 346; }
	public final String getName() { return "EjectGroupMemberReply"; }
	@Sequence(0)
	public EjectGroupMemberReply_bAgentData bagentdata=new EjectGroupMemberReply_bAgentData();
	@Sequence(1)
	public EjectGroupMemberReply_bGroupData bgroupdata=new EjectGroupMemberReply_bGroupData();
	@Sequence(2)
	public EjectGroupMemberReply_bEjectData bejectdata=new EjectGroupMemberReply_bEjectData();
	public EjectGroupMemberReply(){}
	public EjectGroupMemberReply(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
