package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class RemoveMuteListEntry extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 264; }
	@Nonnull
    public final String getName() { return "RemoveMuteListEntry"; }
	@Nonnull
    @Sequence(0)
	public RemoveMuteListEntry_bAgentData bagentdata=new RemoveMuteListEntry_bAgentData();
	@Nonnull
    @Sequence(1)
	public RemoveMuteListEntry_bMuteData bmutedata=new RemoveMuteListEntry_bMuteData();
	public RemoveMuteListEntry(){}
	public RemoveMuteListEntry(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
