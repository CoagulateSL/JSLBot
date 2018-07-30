package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MeanCollisionAlert_bMeanCollision extends Block {
	@Sequence(0)
	public LLUUID vvictim=new LLUUID();
	@Sequence(1)
	public LLUUID vperp=new LLUUID();
	@Sequence(2)
	public U32 vtime=new U32();
	@Sequence(3)
	public F32 vmag=new F32();
	@Sequence(4)
	public U8 vtype=new U8();
}
