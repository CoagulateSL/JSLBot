package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class EventInfoReply_bEventData extends Block {
	@Sequence(0)
	public U32 veventid=new U32();
	@Sequence(1)
	public Variable1 vcreator=new Variable1();
	@Sequence(2)
	public Variable1 vname=new Variable1();
	@Sequence(3)
	public Variable1 vcategory=new Variable1();
	@Sequence(4)
	public Variable2 vdesc=new Variable2();
	@Sequence(5)
	public Variable1 vdate=new Variable1();
	@Sequence(6)
	public U32 vdateutc=new U32();
	@Sequence(7)
	public U32 vduration=new U32();
	@Sequence(8)
	public U32 vcover=new U32();
	@Sequence(9)
	public U32 vamount=new U32();
	@Sequence(10)
	public Variable1 vsimname=new Variable1();
	@Sequence(11)
	public LLVector3d vglobalpos=new LLVector3d();
	@Sequence(12)
	public U32 veventflags=new U32();
}
