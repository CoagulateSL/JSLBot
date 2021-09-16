package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectDetach extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 113; }
	@Nonnull
    public final String getName() { return "ObjectDetach"; }
	@Nonnull
    @Sequence(0)
	public ObjectDetach_bAgentData bagentdata=new ObjectDetach_bAgentData();
	@Sequence(1)
	public List<ObjectDetach_bObjectData> bobjectdata;
	public ObjectDetach(){}
	public ObjectDetach(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
