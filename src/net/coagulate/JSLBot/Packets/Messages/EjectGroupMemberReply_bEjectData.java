package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;

import javax.annotation.Nonnull;

public class EjectGroupMemberReply_bEjectData extends Block {
	@Nonnull
    @Sequence(0)
	public BOOL vsuccess=new BOOL();
}
