package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DirFindQueryBackend extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 32; }
	public final String getName() { return "DirFindQueryBackend"; }
	@Sequence(0)
	public DirFindQueryBackend_bAgentData bagentdata=new DirFindQueryBackend_bAgentData();
	@Sequence(1)
	public DirFindQueryBackend_bQueryData bquerydata=new DirFindQueryBackend_bQueryData();
	public DirFindQueryBackend(){}
	public DirFindQueryBackend(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
