package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ActivateGroup extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 368; }
	public final String getName() { return "ActivateGroup"; }
	@Sequence(0)
	public ActivateGroup_bAgentData bagentdata=new ActivateGroup_bAgentData();
	public ActivateGroup(){}
	public ActivateGroup(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
