package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class DeRezObject extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 291; }
	@Nonnull
    public final String getName() { return "DeRezObject"; }
	@Nonnull
    @Sequence(0)
	public DeRezObject_bAgentData bagentdata=new DeRezObject_bAgentData();
	@Nonnull
    @Sequence(1)
	public DeRezObject_bAgentBlock bagentblock=new DeRezObject_bAgentBlock();
	@Sequence(2)
	public List<DeRezObject_bObjectData> bobjectdata;
	public DeRezObject(){}
	public DeRezObject(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
