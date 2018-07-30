package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelPropertiesUpdate_bParcelData extends Block {
	@Sequence(0)
	public S32 vlocalid=new S32();
	@Sequence(1)
	public U32 vflags=new U32();
	@Sequence(2)
	public U32 vparcelflags=new U32();
	@Sequence(3)
	public S32 vsaleprice=new S32();
	@Sequence(4)
	public Variable1 vname=new Variable1();
	@Sequence(5)
	public Variable1 vdesc=new Variable1();
	@Sequence(6)
	public Variable1 vmusicurl=new Variable1();
	@Sequence(7)
	public Variable1 vmediaurl=new Variable1();
	@Sequence(8)
	public LLUUID vmediaid=new LLUUID();
	@Sequence(9)
	public U8 vmediaautoscale=new U8();
	@Sequence(10)
	public LLUUID vgroupid=new LLUUID();
	@Sequence(11)
	public S32 vpassprice=new S32();
	@Sequence(12)
	public F32 vpasshours=new F32();
	@Sequence(13)
	public U8 vcategory=new U8();
	@Sequence(14)
	public LLUUID vauthbuyerid=new LLUUID();
	@Sequence(15)
	public LLUUID vsnapshotid=new LLUUID();
	@Sequence(16)
	public LLVector3 vuserlocation=new LLVector3();
	@Sequence(17)
	public LLVector3 vuserlookat=new LLVector3();
	@Sequence(18)
	public U8 vlandingtype=new U8();
}
