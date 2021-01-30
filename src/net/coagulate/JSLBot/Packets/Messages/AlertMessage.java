package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AlertMessage extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 134; }
	public final String getName() { return "AlertMessage"; }
	@Sequence(0)
	public AlertMessage_bAlertData balertdata=new AlertMessage_bAlertData();
	@Sequence(1)
	public List<AlertMessage_bAlertInfo> balertinfo;
	@Sequence(2)
	public List<AlertMessage_bAgentInfo> bagentinfo;
}
