package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentThrottle_bThrottle extends Block {
	@Sequence(0)
	public U32 vgencounter=new U32();
	@Sequence(1)
	public Variable1 vthrottles=new Variable1();
}
