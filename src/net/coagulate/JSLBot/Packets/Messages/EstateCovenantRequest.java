package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class EstateCovenantRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 203; }
	@Nonnull
    public final String getName() { return "EstateCovenantRequest"; }
	@Nonnull
    @Sequence(0)
	public EstateCovenantRequest_bAgentData bagentdata=new EstateCovenantRequest_bAgentData();
	public EstateCovenantRequest(){}
	public EstateCovenantRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
