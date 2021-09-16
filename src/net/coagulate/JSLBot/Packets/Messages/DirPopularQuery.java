package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class DirPopularQuery extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 51; }
	@Nonnull
    public final String getName() { return "DirPopularQuery"; }
	@Nonnull
    @Sequence(0)
	public DirPopularQuery_bAgentData bagentdata=new DirPopularQuery_bAgentData();
	@Nonnull
    @Sequence(1)
	public DirPopularQuery_bQueryData bquerydata=new DirPopularQuery_bQueryData();
	public DirPopularQuery(){}
	public DirPopularQuery(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
