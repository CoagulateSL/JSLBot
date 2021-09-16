package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.LLVector3;
import net.coagulate.JSLBot.Packets.Types.U32;

import javax.annotation.Nonnull;

public class ObjectDuplicateOnRay_bAgentData extends Block {
	@Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Sequence(1)
	public LLUUID vsessionid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public LLUUID vgroupid=new LLUUID();
	@Nonnull
    @Sequence(3)
	public LLVector3 vraystart=new LLVector3();
	@Nonnull
    @Sequence(4)
	public LLVector3 vrayend=new LLVector3();
	@Nonnull
    @Sequence(5)
	public BOOL vbypassraycast=new BOOL();
	@Nonnull
    @Sequence(6)
	public BOOL vrayendisintersection=new BOOL();
	@Nonnull
    @Sequence(7)
	public BOOL vcopycenters=new BOOL();
	@Nonnull
    @Sequence(8)
	public BOOL vcopyrotates=new BOOL();
	@Nonnull
    @Sequence(9)
	public LLUUID vraytargetid=new LLUUID();
	@Nonnull
    @Sequence(10)
	public U32 vduplicateflags=new U32();
}
