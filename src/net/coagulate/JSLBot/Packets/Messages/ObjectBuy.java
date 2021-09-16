package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectBuy extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 102; }
	@Nonnull
    public final String getName() { return "ObjectBuy"; }
	@Nonnull
    @Sequence(0)
	public ObjectBuy_bAgentData bagentdata=new ObjectBuy_bAgentData();
	@Sequence(1)
	public List<ObjectBuy_bObjectData> bobjectdata;
	public ObjectBuy(){}
	public ObjectBuy(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
