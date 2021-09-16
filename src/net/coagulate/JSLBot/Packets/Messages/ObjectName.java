package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectName extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 107; }
	@Nonnull
    public final String getName() { return "ObjectName"; }
	@Nonnull
    @Sequence(0)
	public ObjectName_bAgentData bagentdata=new ObjectName_bAgentData();
	@Sequence(1)
	public List<ObjectName_bObjectData> bobjectdata;
	public ObjectName(){}
	public ObjectName(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
