package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class DirLandQuery extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 48; }
	@Nonnull
    public final String getName() { return "DirLandQuery"; }
	@Nonnull
    @Sequence(0)
	public DirLandQuery_bAgentData bagentdata=new DirLandQuery_bAgentData();
	@Nonnull
    @Sequence(1)
	public DirLandQuery_bQueryData bquerydata=new DirLandQuery_bQueryData();
	public DirLandQuery(){}
	public DirLandQuery(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
