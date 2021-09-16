package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class SetStartLocationRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 324; }
	@Nonnull
    public final String getName() { return "SetStartLocationRequest"; }
	@Nonnull
    @Sequence(0)
	public SetStartLocationRequest_bAgentData bagentdata=new SetStartLocationRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public SetStartLocationRequest_bStartLocationData bstartlocationdata=new SetStartLocationRequest_bStartLocationData();
	public SetStartLocationRequest(){}
	public SetStartLocationRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
