package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class LogDwellTime_bDwellInfo extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vsessionid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public F32 vduration=new F32();
	@Nonnull
    @Sequence(3)
	public Variable1 vsimname=new Variable1();
	@Nonnull
    @Sequence(4)
	public U32 vregionx=new U32();
	@Nonnull
    @Sequence(5)
	public U32 vregiony=new U32();
	@Nonnull
    @Sequence(6)
	public U8 vavgagentsinview=new U8();
	@Nonnull
    @Sequence(7)
	public U8 vavgviewerfps=new U8();
}
