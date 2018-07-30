package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupVoteHistoryItemReply_bTransactionData extends Block {
	@Sequence(0)
	public LLUUID vtransactionid=new LLUUID();
	@Sequence(1)
	public U32 vtotalnumitems=new U32();
}
