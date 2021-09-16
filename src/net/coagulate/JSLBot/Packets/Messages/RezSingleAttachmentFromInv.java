package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class RezSingleAttachmentFromInv extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 395; }
	@Nonnull
    public final String getName() { return "RezSingleAttachmentFromInv"; }
	@Nonnull
    @Sequence(0)
	public RezSingleAttachmentFromInv_bAgentData bagentdata=new RezSingleAttachmentFromInv_bAgentData();
	@Nonnull
    @Sequence(1)
	public RezSingleAttachmentFromInv_bObjectData bobjectdata=new RezSingleAttachmentFromInv_bObjectData();
	public RezSingleAttachmentFromInv(){}
	public RezSingleAttachmentFromInv(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
