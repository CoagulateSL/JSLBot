package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ObjectSpinStop extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 122; }
	@Nonnull
    public final String getName() { return "ObjectSpinStop"; }
	@Nonnull
    @Sequence(0)
	public ObjectSpinStop_bAgentData bagentdata=new ObjectSpinStop_bAgentData();
	@Nonnull
    @Sequence(1)
	public ObjectSpinStop_bObjectData bobjectdata=new ObjectSpinStop_bObjectData();
	public ObjectSpinStop(){}
	public ObjectSpinStop(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
