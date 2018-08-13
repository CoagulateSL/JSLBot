package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ClassifiedInfoReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 44; }
	public final String getName() { return "ClassifiedInfoReply"; }
	@Sequence(0)
	public ClassifiedInfoReply_bAgentData bagentdata=new ClassifiedInfoReply_bAgentData();
	@Sequence(1)
	public ClassifiedInfoReply_bData bdata=new ClassifiedInfoReply_bData();
	public ClassifiedInfoReply(){}
	public ClassifiedInfoReply(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
