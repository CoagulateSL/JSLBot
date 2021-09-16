package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectImage extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 96; }
	@Nonnull
    public final String getName() { return "ObjectImage"; }
	@Nonnull
    @Sequence(0)
	public ObjectImage_bAgentData bagentdata=new ObjectImage_bAgentData();
	@Sequence(1)
	public List<ObjectImage_bObjectData> bobjectdata;
	public ObjectImage(){}
	public ObjectImage(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
