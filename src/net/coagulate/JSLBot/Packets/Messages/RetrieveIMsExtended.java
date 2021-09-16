package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class RetrieveIMsExtended extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 427; }
	@Nonnull
    public final String getName() { return "RetrieveIMsExtended"; }
	@Nonnull
    @Sequence(0)
	public RetrieveIMsExtended_bAgentData bagentdata=new RetrieveIMsExtended_bAgentData();
	public RetrieveIMsExtended(){}
	public RetrieveIMsExtended(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
