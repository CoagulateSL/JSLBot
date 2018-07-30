package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ScriptSensorReply_bSensedData extends Block {
	@Sequence(0)
	public LLUUID vobjectid=new LLUUID();
	@Sequence(1)
	public LLUUID vownerid=new LLUUID();
	@Sequence(2)
	public LLUUID vgroupid=new LLUUID();
	@Sequence(3)
	public LLVector3 vposition=new LLVector3();
	@Sequence(4)
	public LLVector3 vvelocity=new LLVector3();
	@Sequence(5)
	public LLQuaternion vrotation=new LLQuaternion();
	@Sequence(6)
	public Variable1 vname=new Variable1();
	@Sequence(7)
	public S32 vtype=new S32();
	@Sequence(8)
	public F32 vrange=new F32();
}
