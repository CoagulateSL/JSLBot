package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectOwner extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 100; }
	@Nonnull
    public final String getName() { return "ObjectOwner"; }
	@Nonnull
    @Sequence(0)
	public ObjectOwner_bAgentData bagentdata=new ObjectOwner_bAgentData();
	@Nonnull
    @Sequence(1)
	public ObjectOwner_bHeaderData bheaderdata=new ObjectOwner_bHeaderData();
	@Sequence(2)
	public List<ObjectOwner_bObjectData> bobjectdata;
	public ObjectOwner(){}
	public ObjectOwner(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
