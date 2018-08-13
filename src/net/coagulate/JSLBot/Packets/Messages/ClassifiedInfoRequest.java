package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ClassifiedInfoRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 43; }
	public final String getName() { return "ClassifiedInfoRequest"; }
	@Sequence(0)
	public ClassifiedInfoRequest_bAgentData bagentdata=new ClassifiedInfoRequest_bAgentData();
	@Sequence(1)
	public ClassifiedInfoRequest_bData bdata=new ClassifiedInfoRequest_bData();
	public ClassifiedInfoRequest(){}
	public ClassifiedInfoRequest(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
