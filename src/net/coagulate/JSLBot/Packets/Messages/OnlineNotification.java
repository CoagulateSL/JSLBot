package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class OnlineNotification extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 322; }
	@Nonnull
    public final String getName() { return "OnlineNotification"; }
	@Sequence(0)
	public List<OnlineNotification_bAgentBlock> bagentblock;
}
