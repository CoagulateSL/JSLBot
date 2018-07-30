package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DirClassifiedReply_bQueryReplies extends Block {
	@Sequence(0)
	public LLUUID vclassifiedid=new LLUUID();
	@Sequence(1)
	public Variable1 vname=new Variable1();
	@Sequence(2)
	public U8 vclassifiedflags=new U8();
	@Sequence(3)
	public U32 vcreationdate=new U32();
	@Sequence(4)
	public U32 vexpirationdate=new U32();
	@Sequence(5)
	public S32 vpriceforlisting=new S32();
}
