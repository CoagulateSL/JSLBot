package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class CreateGroupRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 339; }
	public final String getName() { return "CreateGroupRequest"; }
	@Sequence(0)
	public CreateGroupRequest_bAgentData bagentdata=new CreateGroupRequest_bAgentData();
	@Sequence(1)
	public CreateGroupRequest_bGroupData bgroupdata=new CreateGroupRequest_bGroupData();
	public CreateGroupRequest(){}
	public CreateGroupRequest(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
