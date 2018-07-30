package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SimulatorReady_bSimulatorBlock extends Block {
	@Sequence(0)
	public Variable1 vsimname=new Variable1();
	@Sequence(1)
	public U8 vsimaccess=new U8();
	@Sequence(2)
	public U32 vregionflags=new U32();
	@Sequence(3)
	public LLUUID vregionid=new LLUUID();
	@Sequence(4)
	public U32 vestateid=new U32();
	@Sequence(5)
	public U32 vparentestateid=new U32();
}
