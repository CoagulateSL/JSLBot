package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class LogTextMessage extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 391; }
	public final String getName() { return "LogTextMessage"; }
	@Sequence(0)
	public List<LogTextMessage_bDataBlock> bdatablock;
}
