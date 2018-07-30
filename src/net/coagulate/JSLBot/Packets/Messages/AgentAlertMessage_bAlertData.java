package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AgentAlertMessage_bAlertData extends Block {
	@Sequence(0)
	public BOOL vmodal=new BOOL();
	@Sequence(1)
	public Variable1 vmessage=new Variable1();
}
