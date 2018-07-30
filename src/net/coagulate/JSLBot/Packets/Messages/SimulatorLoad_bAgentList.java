package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SimulatorLoad_bAgentList extends Block {
	@Sequence(0)
	public U32 vcircuitcode=new U32();
	@Sequence(1)
	public U8 vx=new U8();
	@Sequence(2)
	public U8 vy=new U8();
}
