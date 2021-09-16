package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectAttach extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 112; }
	@Nonnull
    public final String getName() { return "ObjectAttach"; }
	@Nonnull
    @Sequence(0)
	public ObjectAttach_bAgentData bagentdata=new ObjectAttach_bAgentData();
	@Sequence(1)
	public List<ObjectAttach_bObjectData> bobjectdata;
	public ObjectAttach(){}
	public ObjectAttach(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
