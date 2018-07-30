package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class OnlineNotification_bAgentBlock extends Block {
	@Sequence(0)
	public LLUUID vagentid=new LLUUID();
}
