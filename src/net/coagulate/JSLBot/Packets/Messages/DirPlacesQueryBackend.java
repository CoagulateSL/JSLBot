package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class DirPlacesQueryBackend extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 34; }
	@Nonnull
    public final String getName() { return "DirPlacesQueryBackend"; }
	@Nonnull
    @Sequence(0)
	public DirPlacesQueryBackend_bAgentData bagentdata=new DirPlacesQueryBackend_bAgentData();
	@Nonnull
    @Sequence(1)
	public DirPlacesQueryBackend_bQueryData bquerydata=new DirPlacesQueryBackend_bQueryData();
	public DirPlacesQueryBackend(){}
	public DirPlacesQueryBackend(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
