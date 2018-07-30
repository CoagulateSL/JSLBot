package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RegionInfo_bRegionInfo2 extends Block {
	@Sequence(0)
	public Variable1 vproductsku=new Variable1();
	@Sequence(1)
	public Variable1 vproductname=new Variable1();
	@Sequence(2)
	public U32 vmaxagents32=new U32();
	@Sequence(3)
	public U32 vhardmaxagents=new U32();
	@Sequence(4)
	public U32 vhardmaxobjects=new U32();
}
