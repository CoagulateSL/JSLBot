package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelInfoReply_bData extends Block {
	@Sequence(0)
	public LLUUID vparcelid=new LLUUID();
	@Sequence(1)
	public LLUUID vownerid=new LLUUID();
	@Sequence(2)
	public Variable1 vname=new Variable1();
	@Sequence(3)
	public Variable1 vdesc=new Variable1();
	@Sequence(4)
	public S32 vactualarea=new S32();
	@Sequence(5)
	public S32 vbillablearea=new S32();
	@Sequence(6)
	public U8 vflags=new U8();
	@Sequence(7)
	public F32 vglobalx=new F32();
	@Sequence(8)
	public F32 vglobaly=new F32();
	@Sequence(9)
	public F32 vglobalz=new F32();
	@Sequence(10)
	public Variable1 vsimname=new Variable1();
	@Sequence(11)
	public LLUUID vsnapshotid=new LLUUID();
	@Sequence(12)
	public F32 vdwell=new F32();
	@Sequence(13)
	public S32 vsaleprice=new S32();
	@Sequence(14)
	public S32 vauctionid=new S32();
}
