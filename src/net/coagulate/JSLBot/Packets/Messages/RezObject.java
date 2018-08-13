package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RezObject extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 293; }
	public final String getName() { return "RezObject"; }
	@Sequence(0)
	public RezObject_bAgentData bagentdata=new RezObject_bAgentData();
	@Sequence(1)
	public RezObject_bRezData brezdata=new RezObject_bRezData();
	@Sequence(2)
	public RezObject_bInventoryData binventorydata=new RezObject_bInventoryData();
	public RezObject(){}
	public RezObject(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
