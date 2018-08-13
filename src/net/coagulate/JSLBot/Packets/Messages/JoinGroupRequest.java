package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class JoinGroupRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 343; }
	public final String getName() { return "JoinGroupRequest"; }
	@Sequence(0)
	public JoinGroupRequest_bAgentData bagentdata=new JoinGroupRequest_bAgentData();
	@Sequence(1)
	public JoinGroupRequest_bGroupData bgroupdata=new JoinGroupRequest_bGroupData();
	public JoinGroupRequest(){}
	public JoinGroupRequest(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
