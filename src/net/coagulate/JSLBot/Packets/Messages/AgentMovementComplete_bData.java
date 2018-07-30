package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentMovementComplete_bData extends Block {
	@Sequence(0)
	public LLVector3 vposition=new LLVector3();
	@Sequence(1)
	public LLVector3 vlookat=new LLVector3();
	@Sequence(2)
	public U64 vregionhandle=new U64();
	@Sequence(3)
	public U32 vtimestamp=new U32();
}
