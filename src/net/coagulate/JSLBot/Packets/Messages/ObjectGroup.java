package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectGroup extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 101; }
	@Nonnull
    public final String getName() { return "ObjectGroup"; }
	@Nonnull
    @Sequence(0)
	public ObjectGroup_bAgentData bagentdata=new ObjectGroup_bAgentData();
	@Sequence(1)
	public List<ObjectGroup_bObjectData> bobjectdata;
	public ObjectGroup(){}
	public ObjectGroup(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
