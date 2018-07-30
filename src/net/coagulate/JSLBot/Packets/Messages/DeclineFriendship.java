package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DeclineFriendship extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 298; }
	public final String getName() { return "DeclineFriendship"; }
	@Sequence(0)
	public DeclineFriendship_bAgentData bagentdata=new DeclineFriendship_bAgentData();
	@Sequence(1)
	public DeclineFriendship_bTransactionBlock btransactionblock=new DeclineFriendship_bTransactionBlock();
}
