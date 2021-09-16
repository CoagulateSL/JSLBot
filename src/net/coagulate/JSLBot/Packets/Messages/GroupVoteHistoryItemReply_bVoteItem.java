package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.S32;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class GroupVoteHistoryItemReply_bVoteItem extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vcandidateid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public Variable1 vvotecast=new Variable1();
	@Nonnull
    @Sequence(2)
	public S32 vnumvotes=new S32();
}
