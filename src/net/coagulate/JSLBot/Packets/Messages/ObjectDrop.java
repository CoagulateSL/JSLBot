package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectDrop extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 114; }
	@Nonnull
    public final String getName() { return "ObjectDrop"; }
	@Nonnull
    @Sequence(0)
	public ObjectDrop_bAgentData bagentdata=new ObjectDrop_bAgentData();
	@Sequence(1)
	public List<ObjectDrop_bObjectData> bobjectdata;
	public ObjectDrop(){}
	public ObjectDrop(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
