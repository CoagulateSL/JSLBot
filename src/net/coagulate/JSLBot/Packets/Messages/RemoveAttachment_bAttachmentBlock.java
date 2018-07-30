package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RemoveAttachment_bAttachmentBlock extends Block {
	@Sequence(0)
	public U8 vattachmentpoint=new U8();
	@Sequence(1)
	public LLUUID vitemid=new LLUUID();
}
