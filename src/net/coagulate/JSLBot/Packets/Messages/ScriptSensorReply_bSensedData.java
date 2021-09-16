package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class ScriptSensorReply_bSensedData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vobjectid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vownerid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public LLUUID vgroupid=new LLUUID();
	@Nonnull
    @Sequence(3)
	public LLVector3 vposition=new LLVector3();
	@Nonnull
    @Sequence(4)
	public LLVector3 vvelocity=new LLVector3();
	@Nonnull
    @Sequence(5)
	public LLQuaternion vrotation=new LLQuaternion();
	@Nonnull
    @Sequence(6)
	public Variable1 vname=new Variable1();
	@Nonnull
    @Sequence(7)
	public S32 vtype=new S32();
	@Nonnull
    @Sequence(8)
	public F32 vrange=new F32();
}
