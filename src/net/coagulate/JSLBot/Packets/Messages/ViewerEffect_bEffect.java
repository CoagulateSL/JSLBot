package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ViewerEffect_bEffect extends Block {
	@Sequence(0)
	public LLUUID vid=new LLUUID();
	@Sequence(1)
	public LLUUID vagentid=new LLUUID();
	@Sequence(2)
	public U8 vtype=new U8();
	@Sequence(3)
	public F32 vduration=new F32();
	@Sequence(4)
	public Fixed4 vcolor=new Fixed4();
	@Sequence(5)
	public Variable1 vtypedata=new Variable1();
}
