package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectClickAction extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 95; }
	@Nonnull
    public final String getName() { return "ObjectClickAction"; }
	@Nonnull
    @Sequence(0)
	public ObjectClickAction_bAgentData bagentdata=new ObjectClickAction_bAgentData();
	@Sequence(1)
	public List<ObjectClickAction_bObjectData> bobjectdata;
	public ObjectClickAction(){}
	public ObjectClickAction(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
