package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectFlagUpdate_bExtraPhysics extends Block {
	@Sequence(0)
	public U8 vphysicsshapetype=new U8();
	@Sequence(1)
	public F32 vdensity=new F32();
	@Sequence(2)
	public F32 vfriction=new F32();
	@Sequence(3)
	public F32 vrestitution=new F32();
	@Sequence(4)
	public F32 vgravitymultiplier=new F32();
}
