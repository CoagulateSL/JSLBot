package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AcceptCallingCard extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 302; }
	public final String getName() { return "AcceptCallingCard"; }
	@Sequence(0)
	public AcceptCallingCard_bAgentData bagentdata=new AcceptCallingCard_bAgentData();
	@Sequence(1)
	public AcceptCallingCard_bTransactionBlock btransactionblock=new AcceptCallingCard_bTransactionBlock();
	@Sequence(2)
	public List<AcceptCallingCard_bFolderData> bfolderdata;
	public AcceptCallingCard(){}
	public AcceptCallingCard(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
