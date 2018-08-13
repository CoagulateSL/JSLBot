package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DirPopularQueryBackend extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 52; }
	public final String getName() { return "DirPopularQueryBackend"; }
	@Sequence(0)
	public DirPopularQueryBackend_bAgentData bagentdata=new DirPopularQueryBackend_bAgentData();
	@Sequence(1)
	public DirPopularQueryBackend_bQueryData bquerydata=new DirPopularQueryBackend_bQueryData();
	public DirPopularQueryBackend(){}
	public DirPopularQueryBackend(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
