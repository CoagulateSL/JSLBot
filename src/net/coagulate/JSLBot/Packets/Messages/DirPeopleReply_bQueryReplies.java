package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DirPeopleReply_bQueryReplies extends Block {
	@Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Sequence(1)
	public Variable1 vfirstname=new Variable1();
	@Sequence(2)
	public Variable1 vlastname=new Variable1();
	@Sequence(3)
	public Variable1 vgroup=new Variable1();
	@Sequence(4)
	public BOOL vonline=new BOOL();
	@Sequence(5)
	public S32 vreputation=new S32();
}
