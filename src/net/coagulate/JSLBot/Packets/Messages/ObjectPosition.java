package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectPosition extends Block implements Message {
	public final int getFrequency() { return Frequency.MEDIUM; }
	public final int getId() { return 4; }
	@Nonnull
    public final String getName() { return "ObjectPosition"; }
	@Nonnull
    @Sequence(0)
	public ObjectPosition_bAgentData bagentdata=new ObjectPosition_bAgentData();
	@Sequence(1)
	public List<ObjectPosition_bObjectData> bobjectdata;
	public ObjectPosition(){}
	public ObjectPosition(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
