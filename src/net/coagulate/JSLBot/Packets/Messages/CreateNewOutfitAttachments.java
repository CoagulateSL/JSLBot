package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class CreateNewOutfitAttachments extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 398; }
	@Nonnull
    public final String getName() { return "CreateNewOutfitAttachments"; }
	@Nonnull
    @Sequence(0)
	public CreateNewOutfitAttachments_bAgentData bagentdata=new CreateNewOutfitAttachments_bAgentData();
	@Nonnull
    @Sequence(1)
	public CreateNewOutfitAttachments_bHeaderData bheaderdata=new CreateNewOutfitAttachments_bHeaderData();
	@Sequence(2)
	public List<CreateNewOutfitAttachments_bObjectData> bobjectdata;
	public CreateNewOutfitAttachments(){}
	public CreateNewOutfitAttachments(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
