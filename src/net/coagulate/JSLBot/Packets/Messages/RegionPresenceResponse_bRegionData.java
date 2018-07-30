package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RegionPresenceResponse_bRegionData extends Block {
	@Sequence(0)
	public LLUUID vregionid=new LLUUID();
	@Sequence(1)
	public U64 vregionhandle=new U64();
	@Sequence(2)
	public IPADDR vinternalregionip=new IPADDR();
	@Sequence(3)
	public IPADDR vexternalregionip=new IPADDR();
	@Sequence(4)
	public IPPORT vregionport=new IPPORT();
	@Sequence(5)
	public F64 vvaliduntil=new F64();
	@Sequence(6)
	public Variable1 vmessage=new Variable1();
}
