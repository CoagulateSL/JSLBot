package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class InviteGroupRequest_bInviteData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vinviteeid=new LLUUID();
	@Nullable
    @Sequence(1)
	public LLUUID vroleid=new LLUUID();
}
