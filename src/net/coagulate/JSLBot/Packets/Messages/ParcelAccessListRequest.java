package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ParcelAccessListRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 215; }
	@Nonnull
    public final String getName() { return "ParcelAccessListRequest"; }
	@Nonnull
    @Sequence(0)
	public ParcelAccessListRequest_bAgentData bagentdata=new ParcelAccessListRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public ParcelAccessListRequest_bData bdata=new ParcelAccessListRequest_bData();
	public ParcelAccessListRequest(){}
	public ParcelAccessListRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
