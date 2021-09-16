package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ParcelDwellRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 218; }
	@Nonnull
    public final String getName() { return "ParcelDwellRequest"; }
	@Nonnull
    @Sequence(0)
	public ParcelDwellRequest_bAgentData bagentdata=new ParcelDwellRequest_bAgentData();
	@Nonnull
    @Sequence(1)
	public ParcelDwellRequest_bData bdata=new ParcelDwellRequest_bData();
	public ParcelDwellRequest(){}
	public ParcelDwellRequest(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
