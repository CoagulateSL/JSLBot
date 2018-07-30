package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentFOV_bFOVBlock extends Block {
	@Sequence(0)
	public U32 vgencounter=new U32();
	@Sequence(1)
	public F32 vverticalangle=new F32();
}
