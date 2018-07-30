package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class OpenCircuit_bCircuitInfo extends Block {
	@Sequence(0)
	public IPADDR vip=new IPADDR();
	@Sequence(1)
	public IPPORT vport=new IPPORT();
}
