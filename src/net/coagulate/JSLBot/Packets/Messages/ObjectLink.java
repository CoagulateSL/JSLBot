package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectLink extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 115; }
	@Nonnull
    public final String getName() { return "ObjectLink"; }
	@Nonnull
    @Sequence(0)
	public ObjectLink_bAgentData bagentdata=new ObjectLink_bAgentData();
	@Sequence(1)
	public List<ObjectLink_bObjectData> bobjectdata;
	public ObjectLink(){}
	public ObjectLink(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
