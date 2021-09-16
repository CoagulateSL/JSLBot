package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ParcelPropertiesRequestByID extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 197; }
	@Nonnull
    public final String getName() { return "ParcelPropertiesRequestByID"; }
	@Nonnull
    @Sequence(0)
	public ParcelPropertiesRequestByID_bAgentData bagentdata=new ParcelPropertiesRequestByID_bAgentData();
	@Nonnull
    @Sequence(1)
	public ParcelPropertiesRequestByID_bParcelData bparceldata=new ParcelPropertiesRequestByID_bParcelData();
	public ParcelPropertiesRequestByID(){}
	public ParcelPropertiesRequestByID(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
