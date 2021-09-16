package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ObjectAdd extends Block implements Message {
	public final int getFrequency() { return Frequency.MEDIUM; }
	public final int getId() { return 1; }
	@Nonnull
    public final String getName() { return "ObjectAdd"; }
	@Nonnull
    @Sequence(0)
	public ObjectAdd_bAgentData bagentdata=new ObjectAdd_bAgentData();
	@Nonnull
    @Sequence(1)
	public ObjectAdd_bObjectData bobjectdata=new ObjectAdd_bObjectData();
	public ObjectAdd(){}
	public ObjectAdd(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
