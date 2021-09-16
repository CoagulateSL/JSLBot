package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class AgentGroupDataUpdate_bGroupData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vgroupid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public U64 vgrouppowers=new U64();
	@Nonnull
    @Sequence(2)
	public BOOL vacceptnotices=new BOOL();
	@Nonnull
    @Sequence(3)
	public LLUUID vgroupinsigniaid=new LLUUID();
	@Nonnull
    @Sequence(4)
	public S32 vcontribution=new S32();
	@Nonnull
    @Sequence(5)
	public Variable1 vgroupname=new Variable1();
}
