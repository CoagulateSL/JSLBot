package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DeclineCallingCard extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 303; }
	public final String getName() { return "DeclineCallingCard"; }
	@Sequence(0)
	public DeclineCallingCard_bAgentData bagentdata=new DeclineCallingCard_bAgentData();
	@Sequence(1)
	public DeclineCallingCard_bTransactionBlock btransactionblock=new DeclineCallingCard_bTransactionBlock();
	public DeclineCallingCard(){}
	public DeclineCallingCard(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
