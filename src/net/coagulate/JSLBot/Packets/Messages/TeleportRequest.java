package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class TeleportRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 62; }
	@Nonnull
    public final String getName() { return "TeleportRequest"; }
	@Nonnull
    @Sequence(0)
	public TeleportRequest_bAgentData bagentdata=new TeleportRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public TeleportRequest_bInfo binfo=new TeleportRequest_bInfo();
	public TeleportRequest(){}
	public TeleportRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
