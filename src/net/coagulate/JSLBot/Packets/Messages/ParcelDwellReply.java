package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelDwellReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 219; }
	public final String getName() { return "ParcelDwellReply"; }
	@Sequence(0)
	public ParcelDwellReply_bAgentData bagentdata=new ParcelDwellReply_bAgentData();
	@Sequence(1)
	public ParcelDwellReply_bData bdata=new ParcelDwellReply_bData();
	public ParcelDwellReply(){}
	public ParcelDwellReply(JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
