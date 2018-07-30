package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UpdateAttachment extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 331; }
	public final String getName() { return "UpdateAttachment"; }
	@Sequence(0)
	public UpdateAttachment_bAgentData bagentdata=new UpdateAttachment_bAgentData();
	@Sequence(1)
	public UpdateAttachment_bAttachmentBlock battachmentblock=new UpdateAttachment_bAttachmentBlock();
	@Sequence(2)
	public UpdateAttachment_bOperationData boperationdata=new UpdateAttachment_bOperationData();
	@Sequence(3)
	public UpdateAttachment_bInventoryData binventorydata=new UpdateAttachment_bInventoryData();
}
