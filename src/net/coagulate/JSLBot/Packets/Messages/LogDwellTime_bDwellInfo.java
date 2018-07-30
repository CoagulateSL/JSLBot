package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class LogDwellTime_bDwellInfo extends Block {
	@Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Sequence(1)
	public LLUUID vsessionid=new LLUUID();
	@Sequence(2)
	public F32 vduration=new F32();
	@Sequence(3)
	public Variable1 vsimname=new Variable1();
	@Sequence(4)
	public U32 vregionx=new U32();
	@Sequence(5)
	public U32 vregiony=new U32();
	@Sequence(6)
	public U8 vavgagentsinview=new U8();
	@Sequence(7)
	public U8 vavgviewerfps=new U8();
}
