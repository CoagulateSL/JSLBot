package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.F32;
import net.coagulate.JSLBot.Packets.Types.LLVector3;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.U64;

import javax.annotation.Nonnull;

public class SimulatorViewerTimeMessage_bTimeInfo extends Block {
	@Nonnull
    @Sequence(0)
	public U64 vusecsincestart=new U64();
	@Nonnull
    @Sequence(1)
	public U32 vsecperday=new U32();
	@Nonnull
    @Sequence(2)
	public U32 vsecperyear=new U32();
	@Nonnull
    @Sequence(3)
	public LLVector3 vsundirection=new LLVector3();
	@Nonnull
    @Sequence(4)
	public F32 vsunphase=new F32();
	@Nonnull
    @Sequence(5)
	public LLVector3 vsunangvelocity=new LLVector3();
}
