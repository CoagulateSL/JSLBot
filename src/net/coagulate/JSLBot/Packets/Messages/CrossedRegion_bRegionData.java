package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class CrossedRegion_bRegionData extends Block {
	@Sequence(0)
	public IPADDR vsimip=new IPADDR();
	@Sequence(1)
	public IPPORT vsimport=new IPPORT();
	@Sequence(2)
	public U64 vregionhandle=new U64();
	@Sequence(3)
	public Variable2 vseedcapability=new Variable2();
}
