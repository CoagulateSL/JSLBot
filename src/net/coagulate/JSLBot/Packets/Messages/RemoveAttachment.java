package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class RemoveAttachment extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 332; }
	@Nonnull
    public final String getName() { return "RemoveAttachment"; }
	@Nonnull
    @Sequence(0)
	public RemoveAttachment_bAgentData bagentdata=new RemoveAttachment_bAgentData();
	@Nonnull
    @Sequence(1)
	public RemoveAttachment_bAttachmentBlock battachmentblock=new RemoveAttachment_bAttachmentBlock();
	public RemoveAttachment(){}
	public RemoveAttachment(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
