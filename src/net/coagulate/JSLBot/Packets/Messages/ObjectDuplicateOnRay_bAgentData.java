package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ObjectDuplicateOnRay_bAgentData extends Block {
	@Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Sequence(1)
	public LLUUID vsessionid=new LLUUID();
	@Sequence(2)
	public LLUUID vgroupid=new LLUUID();
	@Sequence(3)
	public LLVector3 vraystart=new LLVector3();
	@Sequence(4)
	public LLVector3 vrayend=new LLVector3();
	@Sequence(5)
	public BOOL vbypassraycast=new BOOL();
	@Sequence(6)
	public BOOL vrayendisintersection=new BOOL();
	@Sequence(7)
	public BOOL vcopycenters=new BOOL();
	@Sequence(8)
	public BOOL vcopyrotates=new BOOL();
	@Sequence(9)
	public LLUUID vraytargetid=new LLUUID();
	@Sequence(10)
	public U32 vduplicateflags=new U32();
}
