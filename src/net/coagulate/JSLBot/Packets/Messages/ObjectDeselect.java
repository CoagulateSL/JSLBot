package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectDeselect extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 111; }
	@Nonnull
    public final String getName() { return "ObjectDeselect"; }
	@Nonnull
    @Sequence(0)
	public ObjectDeselect_bAgentData bagentdata=new ObjectDeselect_bAgentData();
	@Sequence(1)
	public List<ObjectDeselect_bObjectData> bobjectdata;
	public ObjectDeselect(){}
	public ObjectDeselect(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
