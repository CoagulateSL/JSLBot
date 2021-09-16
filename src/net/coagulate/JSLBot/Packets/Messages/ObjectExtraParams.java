package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectExtraParams extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 99; }
	@Nonnull
    public final String getName() { return "ObjectExtraParams"; }
	@Nonnull
    @Sequence(0)
	public ObjectExtraParams_bAgentData bagentdata=new ObjectExtraParams_bAgentData();
	@Sequence(1)
	public List<ObjectExtraParams_bObjectData> bobjectdata;
	public ObjectExtraParams(){}
	public ObjectExtraParams(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
