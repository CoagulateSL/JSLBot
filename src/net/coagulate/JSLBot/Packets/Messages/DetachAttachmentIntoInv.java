package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DetachAttachmentIntoInv extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 397; }
	public final String getName() { return "DetachAttachmentIntoInv"; }
	@Sequence(0)
	public DetachAttachmentIntoInv_bObjectData bobjectdata=new DetachAttachmentIntoInv_bObjectData();
}
