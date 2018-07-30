package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ClassifiedInfoReply_bData extends Block {
	@Sequence(0)
	public LLUUID vclassifiedid=new LLUUID();
	@Sequence(1)
	public LLUUID vcreatorid=new LLUUID();
	@Sequence(2)
	public U32 vcreationdate=new U32();
	@Sequence(3)
	public U32 vexpirationdate=new U32();
	@Sequence(4)
	public U32 vcategory=new U32();
	@Sequence(5)
	public Variable1 vname=new Variable1();
	@Sequence(6)
	public Variable2 vdesc=new Variable2();
	@Sequence(7)
	public LLUUID vparcelid=new LLUUID();
	@Sequence(8)
	public U32 vparentestate=new U32();
	@Sequence(9)
	public LLUUID vsnapshotid=new LLUUID();
	@Sequence(10)
	public Variable1 vsimname=new Variable1();
	@Sequence(11)
	public LLVector3d vposglobal=new LLVector3d();
	@Sequence(12)
	public Variable1 vparcelname=new Variable1();
	@Sequence(13)
	public U8 vclassifiedflags=new U8();
	@Sequence(14)
	public S32 vpriceforlisting=new S32();
}
