package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ClassifiedInfoUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 45; }
	public final String getName() { return "ClassifiedInfoUpdate"; }
	@Sequence(0)
	public ClassifiedInfoUpdate_bAgentData bagentdata=new ClassifiedInfoUpdate_bAgentData();
	@Sequence(1)
	public ClassifiedInfoUpdate_bData bdata=new ClassifiedInfoUpdate_bData();
	public ClassifiedInfoUpdate(){}
	public ClassifiedInfoUpdate(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
