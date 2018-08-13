package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DirPopularQuery extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 51; }
	public final String getName() { return "DirPopularQuery"; }
	@Sequence(0)
	public DirPopularQuery_bAgentData bagentdata=new DirPopularQuery_bAgentData();
	@Sequence(1)
	public DirPopularQuery_bQueryData bquerydata=new DirPopularQuery_bQueryData();
	public DirPopularQuery(){}
	public DirPopularQuery(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
