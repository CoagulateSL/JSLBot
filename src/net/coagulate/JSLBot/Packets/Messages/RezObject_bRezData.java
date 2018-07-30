package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RezObject_bRezData extends Block {
	@Sequence(0)
	public LLUUID vfromtaskid=new LLUUID();
	@Sequence(1)
	public U8 vbypassraycast=new U8();
	@Sequence(2)
	public LLVector3 vraystart=new LLVector3();
	@Sequence(3)
	public LLVector3 vrayend=new LLVector3();
	@Sequence(4)
	public LLUUID vraytargetid=new LLUUID();
	@Sequence(5)
	public BOOL vrayendisintersection=new BOOL();
	@Sequence(6)
	public BOOL vrezselected=new BOOL();
	@Sequence(7)
	public BOOL vremoveitem=new BOOL();
	@Sequence(8)
	public U32 vitemflags=new U32();
	@Sequence(9)
	public U32 vgroupmask=new U32();
	@Sequence(10)
	public U32 veveryonemask=new U32();
	@Sequence(11)
	public U32 vnextownermask=new U32();
}
