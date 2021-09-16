package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectRotation extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 93; }
	@Nonnull
    public final String getName() { return "ObjectRotation"; }
	@Nonnull
    @Sequence(0)
	public ObjectRotation_bAgentData bagentdata=new ObjectRotation_bAgentData();
	@Sequence(1)
	public List<ObjectRotation_bObjectData> bobjectdata;
	public ObjectRotation(){}
	public ObjectRotation(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
