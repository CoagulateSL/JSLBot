package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class TeleportLocationRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 63; }
	@Nonnull
    public final String getName() { return "TeleportLocationRequest"; }
	@Nonnull
    @Sequence(0)
	public TeleportLocationRequest_bAgentData bagentdata=new TeleportLocationRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public TeleportLocationRequest_bInfo binfo=new TeleportLocationRequest_bInfo();
	public TeleportLocationRequest(){}
	public TeleportLocationRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
