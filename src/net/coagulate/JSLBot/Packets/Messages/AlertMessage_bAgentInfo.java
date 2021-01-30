package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AlertMessage_bAgentInfo extends Block {
	@Sequence(0)
	public LLUUID vagentid=new LLUUID();
}
