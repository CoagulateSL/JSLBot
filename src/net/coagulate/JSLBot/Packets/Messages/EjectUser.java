package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class EjectUser extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 167; }
	public final String getName() { return "EjectUser"; }
	@Sequence(0)
	public EjectUser_bAgentData bagentdata=new EjectUser_bAgentData();
	@Sequence(1)
	public EjectUser_bData bdata=new EjectUser_bData();
	public EjectUser(){}
	public EjectUser(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
