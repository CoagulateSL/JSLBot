package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectCategory extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 109; }
	@Nonnull
    public final String getName() { return "ObjectCategory"; }
	@Nonnull
    @Sequence(0)
	public ObjectCategory_bAgentData bagentdata=new ObjectCategory_bAgentData();
	@Sequence(1)
	public List<ObjectCategory_bObjectData> bobjectdata;
	public ObjectCategory(){}
	public ObjectCategory(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
