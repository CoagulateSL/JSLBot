package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.U8;

import javax.annotation.Nonnull;

public class ChildAgentUpdate_bAgentAccess extends Block {
	@Nonnull
    @Sequence(0)
	public U8 vagentlegacyaccess=new U8();
	@Nonnull
    @Sequence(1)
	public U8 vagentmaxaccess=new U8();
}
