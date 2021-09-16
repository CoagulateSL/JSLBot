package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class MapNameRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 408; }
	@Nonnull
    public final String getName() { return "MapNameRequest"; }
	@Nonnull
    @Sequence(0)
	public MapNameRequest_bAgentData bagentdata=new MapNameRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public MapNameRequest_bNameData bnamedata=new MapNameRequest_bNameData();
	public MapNameRequest(){}
	public MapNameRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
