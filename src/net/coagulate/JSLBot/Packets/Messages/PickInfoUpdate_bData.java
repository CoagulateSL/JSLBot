package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class PickInfoUpdate_bData extends Block {
	@Sequence(0)
	public LLUUID vpickid=new LLUUID();
	@Sequence(1)
	public LLUUID vcreatorid=new LLUUID();
	@Sequence(2)
	public BOOL vtoppick=new BOOL();
	@Sequence(3)
	public LLUUID vparcelid=new LLUUID();
	@Sequence(4)
	public Variable1 vname=new Variable1();
	@Sequence(5)
	public Variable2 vdesc=new Variable2();
	@Sequence(6)
	public LLUUID vsnapshotid=new LLUUID();
	@Sequence(7)
	public LLVector3d vposglobal=new LLVector3d();
	@Sequence(8)
	public S32 vsortorder=new S32();
	@Sequence(9)
	public BOOL venabled=new BOOL();
}
