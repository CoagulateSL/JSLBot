package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectIncludeInSearch extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 424; }
	@Nonnull
    public final String getName() { return "ObjectIncludeInSearch"; }
	@Nonnull
    @Sequence(0)
	public ObjectIncludeInSearch_bAgentData bagentdata=new ObjectIncludeInSearch_bAgentData();
	@Sequence(1)
	public List<ObjectIncludeInSearch_bObjectData> bobjectdata;
	public ObjectIncludeInSearch(){}
	public ObjectIncludeInSearch(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
