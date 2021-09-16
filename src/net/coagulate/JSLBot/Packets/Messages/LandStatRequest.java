package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class LandStatRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 421; }
	@Nonnull
    public final String getName() { return "LandStatRequest"; }
	@Nonnull
    @Sequence(0)
	public LandStatRequest_bAgentData bagentdata=new LandStatRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public LandStatRequest_bRequestData brequestdata=new LandStatRequest_bRequestData();
	public LandStatRequest(){}
	public LandStatRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
