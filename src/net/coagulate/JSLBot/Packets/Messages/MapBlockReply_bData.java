package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MapBlockReply_bData extends Block {
	@Sequence(0)
	public U16 vx=new U16();
	@Sequence(1)
	public U16 vy=new U16();
	@Sequence(2)
	public Variable1 vname=new Variable1();
	@Sequence(3)
	public U8 vaccess=new U8();
	@Sequence(4)
	public U32 vregionflags=new U32();
	@Sequence(5)
	public U8 vwaterheight=new U8();
	@Sequence(6)
	public U8 vagents=new U8();
	@Sequence(7)
	public LLUUID vmapimageid=new LLUUID();
}
