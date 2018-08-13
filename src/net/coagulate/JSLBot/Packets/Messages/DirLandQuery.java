package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DirLandQuery extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 48; }
	public final String getName() { return "DirLandQuery"; }
	@Sequence(0)
	public DirLandQuery_bAgentData bagentdata=new DirLandQuery_bAgentData();
	@Sequence(1)
	public DirLandQuery_bQueryData bquerydata=new DirLandQuery_bQueryData();
	public DirLandQuery(){}
	public DirLandQuery(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
