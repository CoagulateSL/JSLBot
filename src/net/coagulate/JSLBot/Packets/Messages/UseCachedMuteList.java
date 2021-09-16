package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class UseCachedMuteList extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 319; }
	@Nonnull
    public final String getName() { return "UseCachedMuteList"; }
	@Nonnull
    @Sequence(0)
	public UseCachedMuteList_bAgentData bagentdata=new UseCachedMuteList_bAgentData();
	public UseCachedMuteList(){}
	public UseCachedMuteList(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
