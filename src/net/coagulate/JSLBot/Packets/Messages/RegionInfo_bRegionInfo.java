package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RegionInfo_bRegionInfo extends Block {
	@Sequence(0)
	public Variable1 vsimname=new Variable1();
	@Sequence(1)
	public U32 vestateid=new U32();
	@Sequence(2)
	public U32 vparentestateid=new U32();
	@Sequence(3)
	public U32 vregionflags=new U32();
	@Sequence(4)
	public U8 vsimaccess=new U8();
	@Sequence(5)
	public U8 vmaxagents=new U8();
	@Sequence(6)
	public F32 vbillablefactor=new F32();
	@Sequence(7)
	public F32 vobjectbonusfactor=new F32();
	@Sequence(8)
	public F32 vwaterheight=new F32();
	@Sequence(9)
	public F32 vterrainraiselimit=new F32();
	@Sequence(10)
	public F32 vterrainlowerlimit=new F32();
	@Sequence(11)
	public S32 vpricepermeter=new S32();
	@Sequence(12)
	public S32 vredirectgridx=new S32();
	@Sequence(13)
	public S32 vredirectgridy=new S32();
	@Sequence(14)
	public BOOL vuseestatesun=new BOOL();
	@Sequence(15)
	public F32 vsunhour=new F32();
}
