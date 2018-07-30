package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class EconomyData_bInfo extends Block {
	@Sequence(0)
	public S32 vobjectcapacity=new S32();
	@Sequence(1)
	public S32 vobjectcount=new S32();
	@Sequence(2)
	public S32 vpriceenergyunit=new S32();
	@Sequence(3)
	public S32 vpriceobjectclaim=new S32();
	@Sequence(4)
	public S32 vpricepublicobjectdecay=new S32();
	@Sequence(5)
	public S32 vpricepublicobjectdelete=new S32();
	@Sequence(6)
	public S32 vpriceparcelclaim=new S32();
	@Sequence(7)
	public F32 vpriceparcelclaimfactor=new F32();
	@Sequence(8)
	public S32 vpriceupload=new S32();
	@Sequence(9)
	public S32 vpricerentlight=new S32();
	@Sequence(10)
	public S32 vteleportminprice=new S32();
	@Sequence(11)
	public F32 vteleportpriceexponent=new F32();
	@Sequence(12)
	public F32 venergyefficiency=new F32();
	@Sequence(13)
	public F32 vpriceobjectrent=new F32();
	@Sequence(14)
	public F32 vpriceobjectscalefactor=new F32();
	@Sequence(15)
	public S32 vpriceparcelrent=new S32();
	@Sequence(16)
	public S32 vpricegroupcreate=new S32();
}
