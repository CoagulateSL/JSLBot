package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupNoticeRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 60; }
	public final String getName() { return "GroupNoticeRequest"; }
	@Sequence(0)
	public GroupNoticeRequest_bAgentData bagentdata=new GroupNoticeRequest_bAgentData();
	@Sequence(1)
	public GroupNoticeRequest_bData bdata=new GroupNoticeRequest_bData();
	public GroupNoticeRequest(){}
	public GroupNoticeRequest(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
