package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UserInfoRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 399; }
	public final String getName() { return "UserInfoRequest"; }
	@Sequence(0)
	public UserInfoRequest_bAgentData bagentdata=new UserInfoRequest_bAgentData();
}
