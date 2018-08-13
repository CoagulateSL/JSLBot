package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupNoticeAdd extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 61; }
	public final String getName() { return "GroupNoticeAdd"; }
	@Sequence(0)
	public GroupNoticeAdd_bAgentData bagentdata=new GroupNoticeAdd_bAgentData();
	@Sequence(1)
	public GroupNoticeAdd_bMessageBlock bmessageblock=new GroupNoticeAdd_bMessageBlock();
	public GroupNoticeAdd(){}
	public GroupNoticeAdd(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
