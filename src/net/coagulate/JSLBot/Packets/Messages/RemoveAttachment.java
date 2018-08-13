package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RemoveAttachment extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 332; }
	public final String getName() { return "RemoveAttachment"; }
	@Sequence(0)
	public RemoveAttachment_bAgentData bagentdata=new RemoveAttachment_bAgentData();
	@Sequence(1)
	public RemoveAttachment_bAttachmentBlock battachmentblock=new RemoveAttachment_bAttachmentBlock();
	public RemoveAttachment(){}
	public RemoveAttachment(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
