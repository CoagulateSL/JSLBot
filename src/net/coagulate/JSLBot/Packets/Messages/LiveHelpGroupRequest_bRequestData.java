package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class LiveHelpGroupRequest_bRequestData extends Block {
	@Sequence(0)
	public LLUUID vrequestid=new LLUUID();
	@Sequence(1)
	public LLUUID vagentid=new LLUUID();
}
