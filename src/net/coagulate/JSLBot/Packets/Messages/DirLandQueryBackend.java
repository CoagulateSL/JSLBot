package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class DirLandQueryBackend extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 49; }
	@Nonnull
    public final String getName() { return "DirLandQueryBackend"; }
	@Nonnull
    @Sequence(0)
	public DirLandQueryBackend_bAgentData bagentdata=new DirLandQueryBackend_bAgentData();
	@Nonnull
    @Sequence(1)
	public DirLandQueryBackend_bQueryData bquerydata=new DirLandQueryBackend_bQueryData();
	public DirLandQueryBackend(){}
	public DirLandQueryBackend(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
