package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UpdateUserInfo extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 401; }
	public final String getName() { return "UpdateUserInfo"; }
	@Sequence(0)
	public UpdateUserInfo_bAgentData bagentdata=new UpdateUserInfo_bAgentData();
	@Sequence(1)
	public UpdateUserInfo_bUserData buserdata=new UpdateUserInfo_bUserData();
}
