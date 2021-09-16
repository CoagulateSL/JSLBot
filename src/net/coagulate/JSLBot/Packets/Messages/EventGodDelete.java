package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class EventGodDelete extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 183; }
	@Nonnull
    public final String getName() { return "EventGodDelete"; }
	@Nonnull
    @Sequence(0)
	public EventGodDelete_bAgentData bagentdata=new EventGodDelete_bAgentData();
	@Nonnull
    @Sequence(1)
	public EventGodDelete_bEventData beventdata=new EventGodDelete_bEventData();
	@Nonnull
    @Sequence(2)
	public EventGodDelete_bQueryData bquerydata=new EventGodDelete_bQueryData();
	public EventGodDelete(){}
	public EventGodDelete(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
