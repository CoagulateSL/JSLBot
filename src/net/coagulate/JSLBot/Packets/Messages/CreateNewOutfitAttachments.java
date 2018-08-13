package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class CreateNewOutfitAttachments extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 398; }
	public final String getName() { return "CreateNewOutfitAttachments"; }
	@Sequence(0)
	public CreateNewOutfitAttachments_bAgentData bagentdata=new CreateNewOutfitAttachments_bAgentData();
	@Sequence(1)
	public CreateNewOutfitAttachments_bHeaderData bheaderdata=new CreateNewOutfitAttachments_bHeaderData();
	@Sequence(2)
	public List<CreateNewOutfitAttachments_bObjectData> bobjectdata;
	public CreateNewOutfitAttachments(){}
	public CreateNewOutfitAttachments(JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
