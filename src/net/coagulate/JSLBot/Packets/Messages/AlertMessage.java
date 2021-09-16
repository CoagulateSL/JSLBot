package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class AlertMessage extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 134; }
	@Nonnull
    public final String getName() { return "AlertMessage"; }
	@Nonnull
    @Sequence(0)
	public AlertMessage_bAlertData balertdata=new AlertMessage_bAlertData();
	@Sequence(1)
	public List<AlertMessage_bAlertInfo> balertinfo;
	@Sequence(2)
	public List<AlertMessage_bAgentInfo> bagentinfo;
}
