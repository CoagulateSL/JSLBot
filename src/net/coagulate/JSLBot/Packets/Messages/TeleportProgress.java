package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class TeleportProgress extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 66; }
	@Nonnull
    public final String getName() { return "TeleportProgress"; }
	@Nonnull
    @Sequence(0)
	public TeleportProgress_bAgentData bagentdata=new TeleportProgress_bAgentData();
	@Nonnull
    @Sequence(1)
	public TeleportProgress_bInfo binfo=new TeleportProgress_bInfo();
	public TeleportProgress(){}
	public TeleportProgress(@Nonnull JSLBot bot) {
		bagentdata.vagentid=bot.getUUID();
	}
}
