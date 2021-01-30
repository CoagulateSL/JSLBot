package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class JoinGroupRequestExtended extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 428; }
	public final String getName() { return "JoinGroupRequestExtended"; }
	@Sequence(0)
	public JoinGroupRequestExtended_bAgentData bagentdata=new JoinGroupRequestExtended_bAgentData();
	@Sequence(1)
	public JoinGroupRequestExtended_bGroupData bgroupdata=new JoinGroupRequestExtended_bGroupData();
	public JoinGroupRequestExtended(){}
	public JoinGroupRequestExtended(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
