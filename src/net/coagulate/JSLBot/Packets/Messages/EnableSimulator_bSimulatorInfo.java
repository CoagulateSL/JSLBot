package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class EnableSimulator_bSimulatorInfo extends Block {
	@Sequence(0)
	public U64 vhandle=new U64();
	@Sequence(1)
	public IPADDR vip=new IPADDR();
	@Sequence(2)
	public IPPORT vport=new IPPORT();
}
