package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupVoteHistoryItemReply_bVoteItem extends Block {
	@Sequence(0)
	public LLUUID vcandidateid=new LLUUID();
	@Sequence(1)
	public Variable1 vvotecast=new Variable1();
	@Sequence(2)
	public S32 vnumvotes=new S32();
}
