package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class UpdateMuteListEntry extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 263; }
	@Nonnull
    public final String getName() { return "UpdateMuteListEntry"; }
	@Nonnull
    @Sequence(0)
	public UpdateMuteListEntry_bAgentData bagentdata=new UpdateMuteListEntry_bAgentData();
	@Nonnull
    @Sequence(1)
	public UpdateMuteListEntry_bMuteData bmutedata=new UpdateMuteListEntry_bMuteData();
	public UpdateMuteListEntry(){}
	public UpdateMuteListEntry(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
