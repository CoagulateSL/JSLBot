package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class DirClassifiedQueryBackend extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 40; }
	@Nonnull
    public final String getName() { return "DirClassifiedQueryBackend"; }
	@Nonnull
    @Sequence(0)
	public DirClassifiedQueryBackend_bAgentData bagentdata=new DirClassifiedQueryBackend_bAgentData();
	@Nonnull
    @Sequence(1)
	public DirClassifiedQueryBackend_bQueryData bquerydata=new DirClassifiedQueryBackend_bQueryData();
	public DirClassifiedQueryBackend(){}
	public DirClassifiedQueryBackend(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
