package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class RezMultipleAttachmentsFromInv extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 396; }
	@Nonnull
    public final String getName() { return "RezMultipleAttachmentsFromInv"; }
	@Nonnull
    @Sequence(0)
	public RezMultipleAttachmentsFromInv_bAgentData bagentdata=new RezMultipleAttachmentsFromInv_bAgentData();
	@Nonnull
    @Sequence(1)
	public RezMultipleAttachmentsFromInv_bHeaderData bheaderdata=new RezMultipleAttachmentsFromInv_bHeaderData();
	@Sequence(2)
	public List<RezMultipleAttachmentsFromInv_bObjectData> bobjectdata;
	public RezMultipleAttachmentsFromInv(){}
	public RezMultipleAttachmentsFromInv(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
