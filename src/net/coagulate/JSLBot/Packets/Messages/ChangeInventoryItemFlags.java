package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ChangeInventoryItemFlags extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 271; }
	public final String getName() { return "ChangeInventoryItemFlags"; }
	@Sequence(0)
	public ChangeInventoryItemFlags_bAgentData bagentdata=new ChangeInventoryItemFlags_bAgentData();
	@Sequence(1)
	public List<ChangeInventoryItemFlags_bInventoryData> binventorydata;
	public ChangeInventoryItemFlags(){}
	public ChangeInventoryItemFlags(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
