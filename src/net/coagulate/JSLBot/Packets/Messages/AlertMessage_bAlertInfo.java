package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AlertMessage_bAlertInfo extends Block {
	@Sequence(0)
	public Variable1 vmessage=new Variable1();
	@Sequence(1)
	public Variable1 vextraparams=new Variable1();
}
