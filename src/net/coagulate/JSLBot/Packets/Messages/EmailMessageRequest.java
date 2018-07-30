package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class EmailMessageRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 335; }
	public final String getName() { return "EmailMessageRequest"; }
	@Sequence(0)
	public EmailMessageRequest_bDataBlock bdatablock=new EmailMessageRequest_bDataBlock();
}
