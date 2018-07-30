package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class CreateGroupReply_bReplyData extends Block {
	@Sequence(0)
	public LLUUID vgroupid=new LLUUID();
	@Sequence(1)
	public BOOL vsuccess=new BOOL();
	@Sequence(2)
	public Variable1 vmessage=new Variable1();
}
