package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SimulatorViewerTimeMessage_bTimeInfo extends Block {
	@Sequence(0)
	public U64 vusecsincestart=new U64();
	@Sequence(1)
	public U32 vsecperday=new U32();
	@Sequence(2)
	public U32 vsecperyear=new U32();
	@Sequence(3)
	public LLVector3 vsundirection=new LLVector3();
	@Sequence(4)
	public F32 vsunphase=new F32();
	@Sequence(5)
	public LLVector3 vsunangvelocity=new LLVector3();
}
