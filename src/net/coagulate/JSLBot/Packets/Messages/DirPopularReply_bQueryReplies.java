package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DirPopularReply_bQueryReplies extends Block {
	@Sequence(0)
	public LLUUID vparcelid=new LLUUID();
	@Sequence(1)
	public Variable1 vname=new Variable1();
	@Sequence(2)
	public F32 vdwell=new F32();
}
