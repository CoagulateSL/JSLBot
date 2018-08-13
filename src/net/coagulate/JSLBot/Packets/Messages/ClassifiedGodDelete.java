package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ClassifiedGodDelete extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 47; }
	public final String getName() { return "ClassifiedGodDelete"; }
	@Sequence(0)
	public ClassifiedGodDelete_bAgentData bagentdata=new ClassifiedGodDelete_bAgentData();
	@Sequence(1)
	public ClassifiedGodDelete_bData bdata=new ClassifiedGodDelete_bData();
	public ClassifiedGodDelete(){}
	public ClassifiedGodDelete(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
