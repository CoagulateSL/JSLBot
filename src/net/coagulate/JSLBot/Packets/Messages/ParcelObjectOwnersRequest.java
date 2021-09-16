package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ParcelObjectOwnersRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 56; }
	@Nonnull
    public final String getName() { return "ParcelObjectOwnersRequest"; }
	@Nonnull
    @Sequence(0)
	public ParcelObjectOwnersRequest_bAgentData bagentdata=new ParcelObjectOwnersRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public ParcelObjectOwnersRequest_bParcelData bparceldata=new ParcelObjectOwnersRequest_bParcelData();
	public ParcelObjectOwnersRequest(){}
	public ParcelObjectOwnersRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
