package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectDescription extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 108; }
	@Nonnull
    public final String getName() { return "ObjectDescription"; }
	@Nonnull
    @Sequence(0)
	public ObjectDescription_bAgentData bagentdata=new ObjectDescription_bAgentData();
	@Sequence(1)
	public List<ObjectDescription_bObjectData> bobjectdata;
	public ObjectDescription(){}
	public ObjectDescription(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
