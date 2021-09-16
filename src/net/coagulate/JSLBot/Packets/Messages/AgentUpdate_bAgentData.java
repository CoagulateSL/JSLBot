package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class AgentUpdate_bAgentData extends Block {
	@Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Sequence(1)
	public LLUUID vsessionid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public LLQuaternion vbodyrotation=new LLQuaternion();
	@Nonnull
    @Sequence(3)
	public LLQuaternion vheadrotation=new LLQuaternion();
	@Nonnull
    @Sequence(4)
	public U8 vstate=new U8();
	@Nonnull
	@Sequence(5)
	public LLVector3 vcameracenter=new LLVector3();
	@Nonnull
    @Sequence(6)
	public LLVector3 vcameraataxis=new LLVector3();
	@Nonnull
    @Sequence(7)
	public LLVector3 vcameraleftaxis=new LLVector3();
	@Nonnull
    @Sequence(8)
	public LLVector3 vcameraupaxis=new LLVector3();
	@Nonnull
    @Sequence(9)
	public F32 vfar=new F32();
	@Nonnull
    @Sequence(10)
	public U32 vcontrolflags=new U32();
	@Nonnull
    @Sequence(11)
	public U8 vflags=new U8();
}
