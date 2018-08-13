package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class EstateOwnerMessage extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 260; }
	public final String getName() { return "EstateOwnerMessage"; }
	@Sequence(0)
	public EstateOwnerMessage_bAgentData bagentdata=new EstateOwnerMessage_bAgentData();
	@Sequence(1)
	public EstateOwnerMessage_bMethodData bmethoddata=new EstateOwnerMessage_bMethodData();
	@Sequence(2)
	public List<EstateOwnerMessage_bParamList> bparamlist;
	public EstateOwnerMessage(){}
	public EstateOwnerMessage(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
