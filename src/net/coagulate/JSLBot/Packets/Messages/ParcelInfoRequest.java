package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ParcelInfoRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 54; }
	@Nonnull
    public final String getName() { return "ParcelInfoRequest"; }
	@Nonnull
    @Sequence(0)
	public ParcelInfoRequest_bAgentData bagentdata=new ParcelInfoRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public ParcelInfoRequest_bData bdata=new ParcelInfoRequest_bData();
	public ParcelInfoRequest(){}
	public ParcelInfoRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
