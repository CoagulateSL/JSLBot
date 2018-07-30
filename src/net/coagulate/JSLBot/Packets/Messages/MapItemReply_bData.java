package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MapItemReply_bData extends Block {
	@Sequence(0)
	public U32 vx=new U32();
	@Sequence(1)
	public U32 vy=new U32();
	@Sequence(2)
	public LLUUID vid=new LLUUID();
	@Sequence(3)
	public S32 vextra=new S32();
	@Sequence(4)
	public S32 vextra2=new S32();
	@Sequence(5)
	public Variable1 vname=new Variable1();
}
