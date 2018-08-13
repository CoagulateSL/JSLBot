package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class LogoutReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 253; }
	public final String getName() { return "LogoutReply"; }
	@Sequence(0)
	public LogoutReply_bAgentData bagentdata=new LogoutReply_bAgentData();
	@Sequence(1)
	public List<LogoutReply_bInventoryData> binventorydata;
	public LogoutReply(){}
	public LogoutReply(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
