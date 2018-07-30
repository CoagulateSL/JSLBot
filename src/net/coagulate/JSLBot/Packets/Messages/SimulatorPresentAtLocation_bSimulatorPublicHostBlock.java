package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SimulatorPresentAtLocation_bSimulatorPublicHostBlock extends Block {
	@Sequence(0)
	public IPPORT vport=new IPPORT();
	@Sequence(1)
	public IPADDR vsimulatorip=new IPADDR();
	@Sequence(2)
	public U32 vgridx=new U32();
	@Sequence(3)
	public U32 vgridy=new U32();
}
