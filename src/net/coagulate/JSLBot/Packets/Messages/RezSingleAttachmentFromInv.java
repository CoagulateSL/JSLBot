package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RezSingleAttachmentFromInv extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 395; }
	public final String getName() { return "RezSingleAttachmentFromInv"; }
	@Sequence(0)
	public RezSingleAttachmentFromInv_bAgentData bagentdata=new RezSingleAttachmentFromInv_bAgentData();
	@Sequence(1)
	public RezSingleAttachmentFromInv_bObjectData bobjectdata=new RezSingleAttachmentFromInv_bObjectData();
	public RezSingleAttachmentFromInv(){}
	public RezSingleAttachmentFromInv(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
