package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectShape extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 98; }
	@Nonnull
    public final String getName() { return "ObjectShape"; }
	@Nonnull
    @Sequence(0)
	public ObjectShape_bAgentData bagentdata=new ObjectShape_bAgentData();
	@Sequence(1)
	public List<ObjectShape_bObjectData> bobjectdata;
	public ObjectShape(){}
	public ObjectShape(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
