package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectDelete extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 89; }
	@Nonnull
    public final String getName() { return "ObjectDelete"; }
	@Nonnull
    @Sequence(0)
	public ObjectDelete_bAgentData bagentdata=new ObjectDelete_bAgentData();
	@Sequence(1)
	public List<ObjectDelete_bObjectData> bobjectdata;
	public ObjectDelete(){}
	public ObjectDelete(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
