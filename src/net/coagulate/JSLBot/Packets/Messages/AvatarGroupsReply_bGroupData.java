package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U64;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class AvatarGroupsReply_bGroupData extends Block {
	@Nonnull
    @Sequence(0)
	public U64 vgrouppowers=new U64();
	@Nonnull
    @Sequence(1)
	public BOOL vacceptnotices=new BOOL();
	@Nonnull
    @Sequence(2)
	public Variable1 vgrouptitle=new Variable1();
	@Nonnull
    @Sequence(3)
	public LLUUID vgroupid=new LLUUID();
	@Nonnull
    @Sequence(4)
	public Variable1 vgroupname=new Variable1();
	@Nonnull
    @Sequence(5)
	public LLUUID vgroupinsigniaid=new LLUUID();
}
