package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ObjectSaleInfo extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 106; }
	@Nonnull
    public final String getName() { return "ObjectSaleInfo"; }
	@Nonnull
    @Sequence(0)
	public ObjectSaleInfo_bAgentData bagentdata=new ObjectSaleInfo_bAgentData();
	@Sequence(1)
	public List<ObjectSaleInfo_bObjectData> bobjectdata;
	public ObjectSaleInfo(){}
	public ObjectSaleInfo(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
