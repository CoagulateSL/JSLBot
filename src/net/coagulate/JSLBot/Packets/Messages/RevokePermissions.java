package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RevokePermissions extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 193; }
	public final String getName() { return "RevokePermissions"; }
	@Sequence(0)
	public RevokePermissions_bAgentData bagentdata=new RevokePermissions_bAgentData();
	@Sequence(1)
	public RevokePermissions_bData bdata=new RevokePermissions_bData();
	public RevokePermissions(){}
	public RevokePermissions(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
