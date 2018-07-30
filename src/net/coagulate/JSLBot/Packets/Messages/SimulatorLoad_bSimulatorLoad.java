package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SimulatorLoad_bSimulatorLoad extends Block {
	@Sequence(0)
	public F32 vtimedilation=new F32();
	@Sequence(1)
	public S32 vagentcount=new S32();
	@Sequence(2)
	public BOOL vcanacceptagents=new BOOL();
}
