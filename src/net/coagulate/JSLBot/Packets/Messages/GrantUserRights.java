package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GrantUserRights extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 320; }
	public final String getName() { return "GrantUserRights"; }
	@Sequence(0)
	public GrantUserRights_bAgentData bagentdata=new GrantUserRights_bAgentData();
	@Sequence(1)
	public List<GrantUserRights_bRights> brights;
	public GrantUserRights(){}
	public GrantUserRights(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
