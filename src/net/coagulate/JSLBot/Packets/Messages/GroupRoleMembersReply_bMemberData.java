package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;

import javax.annotation.Nonnull;

public class GroupRoleMembersReply_bMemberData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vroleid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vmemberid=new LLUUID();
}
