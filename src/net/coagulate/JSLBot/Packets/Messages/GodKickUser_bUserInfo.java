package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.Variable2;

import javax.annotation.Nonnull;

public class GodKickUser_bUserInfo extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vgodid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vgodsessionid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public LLUUID vagentid=new LLUUID();
	@Nonnull
    @Sequence(3)
	public U32 vkickflags=new U32();
	@Nonnull
    @Sequence(4)
	public Variable2 vreason=new Variable2();
}
