package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class RetrieveInstantMessages extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 255; }
	@Nonnull
    public final String getName() { return "RetrieveInstantMessages"; }
	@Nonnull
    @Sequence(0)
	public RetrieveInstantMessages_bAgentData bagentdata=new RetrieveInstantMessages_bAgentData();
	public RetrieveInstantMessages(){}
	public RetrieveInstantMessages(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
