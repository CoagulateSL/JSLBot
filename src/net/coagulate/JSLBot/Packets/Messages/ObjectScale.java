package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectScale extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 92; }
	@Nonnull
    public final String getName() { return "ObjectScale"; }
	@Nonnull
    @Sequence(0)
	public ObjectScale_bAgentData bagentdata=new ObjectScale_bAgentData();
	@Sequence(1)
	public List<ObjectScale_bObjectData> bobjectdata;
	public ObjectScale(){}
	public ObjectScale(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
