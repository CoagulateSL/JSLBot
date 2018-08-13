package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UserInfoReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 400; }
	public final String getName() { return "UserInfoReply"; }
	@Sequence(0)
	public UserInfoReply_bAgentData bagentdata=new UserInfoReply_bAgentData();
	@Sequence(1)
	public UserInfoReply_bUserData buserdata=new UserInfoReply_bUserData();
	public UserInfoReply(){}
	public UserInfoReply(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
