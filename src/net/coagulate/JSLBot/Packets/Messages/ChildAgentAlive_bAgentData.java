package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.U64;

import javax.annotation.Nonnull;

public class ChildAgentAlive_bAgentData extends Block {
	@Nonnull
    @Sequence(0)
	public U64 vregionhandle=new U64();
	@Nonnull
    @Sequence(1)
	public U32 vviewercircuitcode=new U32();
	@Sequence(2)
	public LLUUID vagentid=new LLUUID();
	@Sequence(3)
	public LLUUID vsessionid=new LLUUID();
}
