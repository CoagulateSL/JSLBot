package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DirEventsReply_bQueryReplies extends Block {
	@Sequence(0)
	public LLUUID vownerid=new LLUUID();
	@Sequence(1)
	public Variable1 vname=new Variable1();
	@Sequence(2)
	public U32 veventid=new U32();
	@Sequence(3)
	public Variable1 vdate=new Variable1();
	@Sequence(4)
	public U32 vunixtime=new U32();
	@Sequence(5)
	public U32 veventflags=new U32();
}
