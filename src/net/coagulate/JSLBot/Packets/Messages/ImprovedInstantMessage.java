package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ImprovedInstantMessage extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 254; }
	public final String getName() { return "ImprovedInstantMessage"; }
	@Sequence(0)
	public ImprovedInstantMessage_bAgentData bagentdata=new ImprovedInstantMessage_bAgentData();
	@Sequence(1)
	public ImprovedInstantMessage_bMessageBlock bmessageblock=new ImprovedInstantMessage_bMessageBlock();
	public ImprovedInstantMessage(){}
	public ImprovedInstantMessage(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
