package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class ViewerStats_bAgentData extends Block {
	@Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Sequence(1)
	public LLUUID vsessionid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public IPADDR vip=new IPADDR();
	@Nonnull
    @Sequence(3)
	public U32 vstarttime=new U32();
	@Nonnull
    @Sequence(4)
	public F32 vruntime=new F32();
	@Nonnull
    @Sequence(5)
	public F32 vsimfps=new F32();
	@Nonnull
    @Sequence(6)
	public F32 vfps=new F32();
	@Nonnull
    @Sequence(7)
	public U8 vagentsinview=new U8();
	@Nonnull
    @Sequence(8)
	public F32 vping=new F32();
	@Nonnull
    @Sequence(9)
	public F64 vmeterstraveled=new F64();
	@Nonnull
    @Sequence(10)
	public S32 vregionsvisited=new S32();
	@Nonnull
    @Sequence(11)
	public U32 vsysram=new U32();
	@Nonnull
    @Sequence(12)
	public Variable1 vsysos=new Variable1();
	@Nonnull
    @Sequence(13)
	public Variable1 vsyscpu=new Variable1();
	@Nonnull
    @Sequence(14)
	public Variable1 vsysgpu=new Variable1();
}
