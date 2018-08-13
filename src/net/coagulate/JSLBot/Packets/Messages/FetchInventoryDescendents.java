package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class FetchInventoryDescendents extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 277; }
	public final String getName() { return "FetchInventoryDescendents"; }
	@Sequence(0)
	public FetchInventoryDescendents_bAgentData bagentdata=new FetchInventoryDescendents_bAgentData();
	@Sequence(1)
	public FetchInventoryDescendents_bInventoryData binventorydata=new FetchInventoryDescendents_bInventoryData();
	public FetchInventoryDescendents(){}
	public FetchInventoryDescendents(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
