package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SetAlwaysRun extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 88; }
	public final String getName() { return "SetAlwaysRun"; }
	@Sequence(0)
	public SetAlwaysRun_bAgentData bagentdata=new SetAlwaysRun_bAgentData();
	public SetAlwaysRun(){}
	public SetAlwaysRun(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
