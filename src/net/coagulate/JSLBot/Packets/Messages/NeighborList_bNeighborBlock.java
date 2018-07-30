package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class NeighborList_bNeighborBlock extends Block {
	@Sequence(0)
	public IPADDR vip=new IPADDR();
	@Sequence(1)
	public IPPORT vport=new IPPORT();
	@Sequence(2)
	public IPADDR vpublicip=new IPADDR();
	@Sequence(3)
	public IPPORT vpublicport=new IPPORT();
	@Sequence(4)
	public LLUUID vregionid=new LLUUID();
	@Sequence(5)
	public Variable1 vname=new Variable1();
	@Sequence(6)
	public U8 vsimaccess=new U8();
}
