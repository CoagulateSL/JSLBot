package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ObjectSpinStart extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 120; }
	@Nonnull
    public final String getName() { return "ObjectSpinStart"; }
	@Nonnull
    @Sequence(0)
	public ObjectSpinStart_bAgentData bagentdata=new ObjectSpinStart_bAgentData();
	@Nonnull
    @Sequence(1)
	public ObjectSpinStart_bObjectData bobjectdata=new ObjectSpinStart_bObjectData();
	public ObjectSpinStart(){}
	public ObjectSpinStart(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
