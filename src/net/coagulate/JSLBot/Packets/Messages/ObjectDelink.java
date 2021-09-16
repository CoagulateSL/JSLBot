package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectDelink extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 116; }
	@Nonnull
    public final String getName() { return "ObjectDelink"; }
	@Nonnull
    @Sequence(0)
	public ObjectDelink_bAgentData bagentdata=new ObjectDelink_bAgentData();
	@Sequence(1)
	public List<ObjectDelink_bObjectData> bobjectdata;
	public ObjectDelink(){}
	public ObjectDelink(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
