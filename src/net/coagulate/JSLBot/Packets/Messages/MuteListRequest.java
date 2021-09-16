package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class MuteListRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 262; }
	@Nonnull
    public final String getName() { return "MuteListRequest"; }
	@Nonnull
    @Sequence(0)
	public MuteListRequest_bAgentData bagentdata=new MuteListRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public MuteListRequest_bMuteData bmutedata=new MuteListRequest_bMuteData();
	public MuteListRequest(){}
	public MuteListRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
