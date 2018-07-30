package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AcceptFriendship extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 297; }
	public final String getName() { return "AcceptFriendship"; }
	@Sequence(0)
	public AcceptFriendship_bAgentData bagentdata=new AcceptFriendship_bAgentData();
	@Sequence(1)
	public AcceptFriendship_bTransactionBlock btransactionblock=new AcceptFriendship_bTransactionBlock();
	@Sequence(2)
	public List<AcceptFriendship_bFolderData> bfolderdata;
}
