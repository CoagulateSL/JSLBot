package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupNoticesListReply_bData extends Block {
	@Sequence(0)
	public LLUUID vnoticeid=new LLUUID();
	@Sequence(1)
	public U32 vtimestamp=new U32();
	@Sequence(2)
	public Variable2 vfromname=new Variable2();
	@Sequence(3)
	public Variable2 vsubject=new Variable2();
	@Sequence(4)
	public BOOL vhasattachment=new BOOL();
	@Sequence(5)
	public U8 vassettype=new U8();
}
