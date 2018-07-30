package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RegionHandshake_bRegionInfo extends Block {
	@Sequence(0)
	public U32 vregionflags=new U32();
	@Sequence(1)
	public U8 vsimaccess=new U8();
	@Sequence(2)
	public Variable1 vsimname=new Variable1();
	@Sequence(3)
	public LLUUID vsimowner=new LLUUID();
	@Sequence(4)
	public BOOL visestatemanager=new BOOL();
	@Sequence(5)
	public F32 vwaterheight=new F32();
	@Sequence(6)
	public F32 vbillablefactor=new F32();
	@Sequence(7)
	public LLUUID vcacheid=new LLUUID();
	@Sequence(8)
	public LLUUID vterrainbase0=new LLUUID();
	@Sequence(9)
	public LLUUID vterrainbase1=new LLUUID();
	@Sequence(10)
	public LLUUID vterrainbase2=new LLUUID();
	@Sequence(11)
	public LLUUID vterrainbase3=new LLUUID();
	@Sequence(12)
	public LLUUID vterraindetail0=new LLUUID();
	@Sequence(13)
	public LLUUID vterraindetail1=new LLUUID();
	@Sequence(14)
	public LLUUID vterraindetail2=new LLUUID();
	@Sequence(15)
	public LLUUID vterraindetail3=new LLUUID();
	@Sequence(16)
	public F32 vterrainstartheight00=new F32();
	@Sequence(17)
	public F32 vterrainstartheight01=new F32();
	@Sequence(18)
	public F32 vterrainstartheight10=new F32();
	@Sequence(19)
	public F32 vterrainstartheight11=new F32();
	@Sequence(20)
	public F32 vterrainheightrange00=new F32();
	@Sequence(21)
	public F32 vterrainheightrange01=new F32();
	@Sequence(22)
	public F32 vterrainheightrange10=new F32();
	@Sequence(23)
	public F32 vterrainheightrange11=new F32();
}
