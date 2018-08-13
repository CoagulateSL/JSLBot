package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelDeedToGroup extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 207; }
	public final String getName() { return "ParcelDeedToGroup"; }
	@Sequence(0)
	public ParcelDeedToGroup_bAgentData bagentdata=new ParcelDeedToGroup_bAgentData();
	@Sequence(1)
	public ParcelDeedToGroup_bData bdata=new ParcelDeedToGroup_bData();
	public ParcelDeedToGroup(){}
	public ParcelDeedToGroup(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
