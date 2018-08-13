package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RezMultipleAttachmentsFromInv extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 396; }
	public final String getName() { return "RezMultipleAttachmentsFromInv"; }
	@Sequence(0)
	public RezMultipleAttachmentsFromInv_bAgentData bagentdata=new RezMultipleAttachmentsFromInv_bAgentData();
	@Sequence(1)
	public RezMultipleAttachmentsFromInv_bHeaderData bheaderdata=new RezMultipleAttachmentsFromInv_bHeaderData();
	@Sequence(2)
	public List<RezMultipleAttachmentsFromInv_bObjectData> bobjectdata;
	public RezMultipleAttachmentsFromInv(){}
	public RezMultipleAttachmentsFromInv(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
