package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GodUpdateRegionInfo_bRegionInfo extends Block {
	@Sequence(0)
	public Variable1 vsimname=new Variable1();
	@Sequence(1)
	public U32 vestateid=new U32();
	@Sequence(2)
	public U32 vparentestateid=new U32();
	@Sequence(3)
	public U32 vregionflags=new U32();
	@Sequence(4)
	public F32 vbillablefactor=new F32();
	@Sequence(5)
	public S32 vpricepermeter=new S32();
	@Sequence(6)
	public S32 vredirectgridx=new S32();
	@Sequence(7)
	public S32 vredirectgridy=new S32();
}
