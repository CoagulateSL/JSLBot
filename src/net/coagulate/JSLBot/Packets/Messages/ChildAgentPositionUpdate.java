package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ChildAgentPositionUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 27; }
	public final String getName() { return "ChildAgentPositionUpdate"; }
	@Sequence(0)
	public ChildAgentPositionUpdate_bAgentData bagentdata=new ChildAgentPositionUpdate_bAgentData();
	public ChildAgentPositionUpdate(){}
	public ChildAgentPositionUpdate(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
