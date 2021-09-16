package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class DirFindQueryBackend extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 32; }
	@Nonnull
    public final String getName() { return "DirFindQueryBackend"; }
	@Nonnull
    @Sequence(0)
	public DirFindQueryBackend_bAgentData bagentdata=new DirFindQueryBackend_bAgentData();
	@Nonnull
    @Sequence(1)
	public DirFindQueryBackend_bQueryData bquerydata=new DirFindQueryBackend_bQueryData();
	public DirFindQueryBackend(){}
	public DirFindQueryBackend(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
