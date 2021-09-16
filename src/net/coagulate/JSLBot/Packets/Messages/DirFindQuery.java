package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class DirFindQuery extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 31; }
	@Nonnull
    public final String getName() { return "DirFindQuery"; }
	@Nonnull
    @Sequence(0)
	public DirFindQuery_bAgentData bagentdata=new DirFindQuery_bAgentData();
	@Nonnull
    @Sequence(1)
	public DirFindQuery_bQueryData bquerydata=new DirFindQuery_bQueryData();
	public DirFindQuery(){}
	public DirFindQuery(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
