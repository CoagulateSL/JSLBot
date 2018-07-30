package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentUpdate_bAgentData extends Block {
	@Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Sequence(1)
	public LLUUID vsessionid=new LLUUID();
	@Sequence(2)
	public LLQuaternion vbodyrotation=new LLQuaternion();
	@Sequence(3)
	public LLQuaternion vheadrotation=new LLQuaternion();
	@Sequence(4)
	public U8 vstate=new U8();
	@Sequence(5)
	public LLVector3 vcameracenter=new LLVector3();
	@Sequence(6)
	public LLVector3 vcameraataxis=new LLVector3();
	@Sequence(7)
	public LLVector3 vcameraleftaxis=new LLVector3();
	@Sequence(8)
	public LLVector3 vcameraupaxis=new LLVector3();
	@Sequence(9)
	public F32 vfar=new F32();
	@Sequence(10)
	public U32 vcontrolflags=new U32();
	@Sequence(11)
	public U8 vflags=new U8();
}
