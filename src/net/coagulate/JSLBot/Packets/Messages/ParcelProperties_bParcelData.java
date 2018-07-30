package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelProperties_bParcelData extends Block {
	@Sequence(0)
	public S32 vrequestresult=new S32();
	@Sequence(1)
	public S32 vsequenceid=new S32();
	@Sequence(2)
	public BOOL vsnapselection=new BOOL();
	@Sequence(3)
	public S32 vselfcount=new S32();
	@Sequence(4)
	public S32 vothercount=new S32();
	@Sequence(5)
	public S32 vpubliccount=new S32();
	@Sequence(6)
	public S32 vlocalid=new S32();
	@Sequence(7)
	public LLUUID vownerid=new LLUUID();
	@Sequence(8)
	public BOOL visgroupowned=new BOOL();
	@Sequence(9)
	public U32 vauctionid=new U32();
	@Sequence(10)
	public S32 vclaimdate=new S32();
	@Sequence(11)
	public S32 vclaimprice=new S32();
	@Sequence(12)
	public S32 vrentprice=new S32();
	@Sequence(13)
	public LLVector3 vaabbmin=new LLVector3();
	@Sequence(14)
	public LLVector3 vaabbmax=new LLVector3();
	@Sequence(15)
	public Variable2 vbitmap=new Variable2();
	@Sequence(16)
	public S32 varea=new S32();
	@Sequence(17)
	public U8 vstatus=new U8();
	@Sequence(18)
	public S32 vsimwidemaxprims=new S32();
	@Sequence(19)
	public S32 vsimwidetotalprims=new S32();
	@Sequence(20)
	public S32 vmaxprims=new S32();
	@Sequence(21)
	public S32 vtotalprims=new S32();
	@Sequence(22)
	public S32 vownerprims=new S32();
	@Sequence(23)
	public S32 vgroupprims=new S32();
	@Sequence(24)
	public S32 votherprims=new S32();
	@Sequence(25)
	public S32 vselectedprims=new S32();
	@Sequence(26)
	public F32 vparcelprimbonus=new F32();
	@Sequence(27)
	public S32 vothercleantime=new S32();
	@Sequence(28)
	public U32 vparcelflags=new U32();
	@Sequence(29)
	public S32 vsaleprice=new S32();
	@Sequence(30)
	public Variable1 vname=new Variable1();
	@Sequence(31)
	public Variable1 vdesc=new Variable1();
	@Sequence(32)
	public Variable1 vmusicurl=new Variable1();
	@Sequence(33)
	public Variable1 vmediaurl=new Variable1();
	@Sequence(34)
	public LLUUID vmediaid=new LLUUID();
	@Sequence(35)
	public U8 vmediaautoscale=new U8();
	@Sequence(36)
	public LLUUID vgroupid=new LLUUID();
	@Sequence(37)
	public S32 vpassprice=new S32();
	@Sequence(38)
	public F32 vpasshours=new F32();
	@Sequence(39)
	public U8 vcategory=new U8();
	@Sequence(40)
	public LLUUID vauthbuyerid=new LLUUID();
	@Sequence(41)
	public LLUUID vsnapshotid=new LLUUID();
	@Sequence(42)
	public LLVector3 vuserlocation=new LLVector3();
	@Sequence(43)
	public LLVector3 vuserlookat=new LLVector3();
	@Sequence(44)
	public U8 vlandingtype=new U8();
	@Sequence(45)
	public BOOL vregionpushoverride=new BOOL();
	@Sequence(46)
	public BOOL vregiondenyanonymous=new BOOL();
	@Sequence(47)
	public BOOL vregiondenyidentified=new BOOL();
	@Sequence(48)
	public BOOL vregiondenytransacted=new BOOL();
}
