package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class DirPopularQueryBackend extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 52; }
	@Nonnull
    public final String getName() { return "DirPopularQueryBackend"; }
	@Nonnull
    @Sequence(0)
	public DirPopularQueryBackend_bAgentData bagentdata=new DirPopularQueryBackend_bAgentData();
	@Nonnull
    @Sequence(1)
	public DirPopularQueryBackend_bQueryData bquerydata=new DirPopularQueryBackend_bQueryData();
	public DirPopularQueryBackend(){}
	public DirPopularQueryBackend(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
