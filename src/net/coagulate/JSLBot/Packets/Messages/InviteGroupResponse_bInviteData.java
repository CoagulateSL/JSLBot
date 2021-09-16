package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.S32;

import javax.annotation.Nonnull;

public class InviteGroupResponse_bInviteData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vinviteeid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public LLUUID vgroupid=new LLUUID();
	@Nonnull
    @Sequence(3)
	public LLUUID vroleid=new LLUUID();
	@Nonnull
    @Sequence(4)
	public S32 vmembershipfee=new S32();
}
