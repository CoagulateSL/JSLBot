package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DirLandQueryBackend extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 49; }
	public final String getName() { return "DirLandQueryBackend"; }
	@Sequence(0)
	public DirLandQueryBackend_bAgentData bagentdata=new DirLandQueryBackend_bAgentData();
	@Sequence(1)
	public DirLandQueryBackend_bQueryData bquerydata=new DirLandQueryBackend_bQueryData();
	public DirLandQueryBackend(){}
	public DirLandQueryBackend(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
