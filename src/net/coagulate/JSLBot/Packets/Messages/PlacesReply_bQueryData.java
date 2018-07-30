package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class PlacesReply_bQueryData extends Block {
	@Sequence(0)
	public LLUUID vownerid=new LLUUID();
	@Sequence(1)
	public Variable1 vname=new Variable1();
	@Sequence(2)
	public Variable1 vdesc=new Variable1();
	@Sequence(3)
	public S32 vactualarea=new S32();
	@Sequence(4)
	public S32 vbillablearea=new S32();
	@Sequence(5)
	public U8 vflags=new U8();
	@Sequence(6)
	public F32 vglobalx=new F32();
	@Sequence(7)
	public F32 vglobaly=new F32();
	@Sequence(8)
	public F32 vglobalz=new F32();
	@Sequence(9)
	public Variable1 vsimname=new Variable1();
	@Sequence(10)
	public LLUUID vsnapshotid=new LLUUID();
	@Sequence(11)
	public F32 vdwell=new F32();
	@Sequence(12)
	public S32 vprice=new S32();
}
