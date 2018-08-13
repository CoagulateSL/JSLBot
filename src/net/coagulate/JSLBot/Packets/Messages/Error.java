package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class Error extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 423; }
	public final String getName() { return "Error"; }
	@Sequence(0)
	public Error_bAgentData bagentdata=new Error_bAgentData();
	@Sequence(1)
	public Error_bData bdata=new Error_bData();
	public Error(){}
	public Error(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
