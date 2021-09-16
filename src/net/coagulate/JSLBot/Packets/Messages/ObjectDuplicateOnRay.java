package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectDuplicateOnRay extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 91; }
	@Nonnull
    public final String getName() { return "ObjectDuplicateOnRay"; }
	@Nonnull
    @Sequence(0)
	public ObjectDuplicateOnRay_bAgentData bagentdata=new ObjectDuplicateOnRay_bAgentData();
	@Sequence(1)
	public List<ObjectDuplicateOnRay_bObjectData> bobjectdata;
	public ObjectDuplicateOnRay(){}
	public ObjectDuplicateOnRay(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
