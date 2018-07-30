package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ClassifiedInfoUpdate_bData extends Block {
	@Sequence(0)
	public LLUUID vclassifiedid=new LLUUID();
	@Sequence(1)
	public U32 vcategory=new U32();
	@Sequence(2)
	public Variable1 vname=new Variable1();
	@Sequence(3)
	public Variable2 vdesc=new Variable2();
	@Sequence(4)
	public LLUUID vparcelid=new LLUUID();
	@Sequence(5)
	public U32 vparentestate=new U32();
	@Sequence(6)
	public LLUUID vsnapshotid=new LLUUID();
	@Sequence(7)
	public LLVector3d vposglobal=new LLVector3d();
	@Sequence(8)
	public U8 vclassifiedflags=new U8();
	@Sequence(9)
	public S32 vpriceforlisting=new S32();
}
