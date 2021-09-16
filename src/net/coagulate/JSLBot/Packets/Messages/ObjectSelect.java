package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectSelect extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 110; }
	@Nonnull
    public final String getName() { return "ObjectSelect"; }
	@Nonnull
    @Sequence(0)
	public ObjectSelect_bAgentData bagentdata=new ObjectSelect_bAgentData();
	@Sequence(1)
	public List<ObjectSelect_bObjectData> bobjectdata;
	public ObjectSelect(){}
	public ObjectSelect(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
