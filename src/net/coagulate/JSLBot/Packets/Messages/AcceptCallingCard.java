package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class AcceptCallingCard extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 302; }
	@Nonnull
	public final String getName() { return "AcceptCallingCard"; }
	@Nonnull
	@Sequence(0)
	public AcceptCallingCard_bAgentData bagentdata=new AcceptCallingCard_bAgentData();
	@Nonnull
	@Sequence(1)
	public AcceptCallingCard_bTransactionBlock btransactionblock=new AcceptCallingCard_bTransactionBlock();
	@Sequence(2)
	public List<AcceptCallingCard_bFolderData> bfolderdata;
	public AcceptCallingCard(){}
	public AcceptCallingCard(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
