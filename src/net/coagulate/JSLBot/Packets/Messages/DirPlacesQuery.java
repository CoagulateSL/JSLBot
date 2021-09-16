package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class DirPlacesQuery extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 33; }
	@Nonnull
    public final String getName() { return "DirPlacesQuery"; }
	@Nonnull
    @Sequence(0)
	public DirPlacesQuery_bAgentData bagentdata=new DirPlacesQuery_bAgentData();
	@Nonnull
    @Sequence(1)
	public DirPlacesQuery_bQueryData bquerydata=new DirPlacesQuery_bQueryData();
	public DirPlacesQuery(){}
	public DirPlacesQuery(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
