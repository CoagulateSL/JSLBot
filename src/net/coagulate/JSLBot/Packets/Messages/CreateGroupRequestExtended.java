package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class CreateGroupRequestExtended extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 429; }
	public final String getName() { return "CreateGroupRequestExtended"; }
	@Sequence(0)
	public CreateGroupRequestExtended_bAgentData bagentdata=new CreateGroupRequestExtended_bAgentData();
	@Sequence(1)
	public CreateGroupRequestExtended_bGroupData bgroupdata=new CreateGroupRequestExtended_bGroupData();
	public CreateGroupRequestExtended(){}
	public CreateGroupRequestExtended(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
