package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SetGroupContribution extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 369; }
	public final String getName() { return "SetGroupContribution"; }
	@Sequence(0)
	public SetGroupContribution_bAgentData bagentdata=new SetGroupContribution_bAgentData();
	@Sequence(1)
	public SetGroupContribution_bData bdata=new SetGroupContribution_bData();
	public SetGroupContribution(){}
	public SetGroupContribution(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
