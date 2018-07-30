package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class LiveHelpGroupReply_bReplyData extends Block {
	@Sequence(0)
	public LLUUID vrequestid=new LLUUID();
	@Sequence(1)
	public LLUUID vgroupid=new LLUUID();
	@Sequence(2)
	public Variable1 vselection=new Variable1();
}
