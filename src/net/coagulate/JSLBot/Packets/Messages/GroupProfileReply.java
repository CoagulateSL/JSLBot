package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupProfileReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 352; }
	public final String getName() { return "GroupProfileReply"; }
	@Sequence(0)
	public GroupProfileReply_bAgentData bagentdata=new GroupProfileReply_bAgentData();
	@Sequence(1)
	public GroupProfileReply_bGroupData bgroupdata=new GroupProfileReply_bGroupData();
	public GroupProfileReply(){}
	public GroupProfileReply(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
