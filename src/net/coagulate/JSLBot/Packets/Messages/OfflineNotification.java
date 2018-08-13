package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class OfflineNotification extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 323; }
	public final String getName() { return "OfflineNotification"; }
	@Sequence(0)
	public List<OfflineNotification_bAgentBlock> bagentblock;
}
