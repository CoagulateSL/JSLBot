package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class AcceptFriendship extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 297; }
	@Nonnull
    public final String getName() { return "AcceptFriendship"; }
	@Nonnull
    @Sequence(0)
	public AcceptFriendship_bAgentData bagentdata=new AcceptFriendship_bAgentData();
	@Nonnull
    @Sequence(1)
	public AcceptFriendship_bTransactionBlock btransactionblock=new AcceptFriendship_bTransactionBlock();
	@Sequence(2)
	public List<AcceptFriendship_bFolderData> bfolderdata;
	public AcceptFriendship(){}
	public AcceptFriendship(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
