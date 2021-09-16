package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectDuplicate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 90; }
	@Nonnull
    public final String getName() { return "ObjectDuplicate"; }
	@Nonnull
    @Sequence(0)
	public ObjectDuplicate_bAgentData bagentdata=new ObjectDuplicate_bAgentData();
	@Nonnull
    @Sequence(1)
	public ObjectDuplicate_bSharedData bshareddata=new ObjectDuplicate_bSharedData();
	@Sequence(2)
	public List<ObjectDuplicate_bObjectData> bobjectdata;
	public ObjectDuplicate(){}
	public ObjectDuplicate(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
