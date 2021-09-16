package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U8;

import javax.annotation.Nonnull;

public class AvatarPropertiesRequestBackend_bAgentData extends Block {
	@Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vavatarid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public U8 vgodlevel=new U8();
	@Nonnull
    @Sequence(3)
	public BOOL vwebprofilesdisabled=new BOOL();
}
