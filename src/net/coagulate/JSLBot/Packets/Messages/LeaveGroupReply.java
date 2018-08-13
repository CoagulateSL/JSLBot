package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class LeaveGroupReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 348; }
	public final String getName() { return "LeaveGroupReply"; }
	@Sequence(0)
	public LeaveGroupReply_bAgentData bagentdata=new LeaveGroupReply_bAgentData();
	@Sequence(1)
	public LeaveGroupReply_bGroupData bgroupdata=new LeaveGroupReply_bGroupData();
	public LeaveGroupReply(){}
	public LeaveGroupReply(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
