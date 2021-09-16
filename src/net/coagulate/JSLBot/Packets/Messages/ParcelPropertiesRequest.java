package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ParcelPropertiesRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.MEDIUM; }
	public final int getId() { return 11; }
	@Nonnull
    public final String getName() { return "ParcelPropertiesRequest"; }
	@Nonnull
    @Sequence(0)
	public ParcelPropertiesRequest_bAgentData bagentdata=new ParcelPropertiesRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public ParcelPropertiesRequest_bParcelData bparceldata=new ParcelPropertiesRequest_bParcelData();
	public ParcelPropertiesRequest(){}
	public ParcelPropertiesRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
