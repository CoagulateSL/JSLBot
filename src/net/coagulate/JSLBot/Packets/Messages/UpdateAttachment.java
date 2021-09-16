package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class UpdateAttachment extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 331; }
	@Nonnull
    public final String getName() { return "UpdateAttachment"; }
	@Nonnull
    @Sequence(0)
	public UpdateAttachment_bAgentData bagentdata=new UpdateAttachment_bAgentData();
	@Nonnull
    @Sequence(1)
	public UpdateAttachment_bAttachmentBlock battachmentblock=new UpdateAttachment_bAttachmentBlock();
	@Nonnull
    @Sequence(2)
	public UpdateAttachment_bOperationData boperationdata=new UpdateAttachment_bOperationData();
	@Nonnull
    @Sequence(3)
	public UpdateAttachment_bInventoryData binventorydata=new UpdateAttachment_bInventoryData();
	public UpdateAttachment(){}
	public UpdateAttachment(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
