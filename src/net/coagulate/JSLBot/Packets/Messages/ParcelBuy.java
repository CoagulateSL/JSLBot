package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ParcelBuy extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 213; }
	@Nonnull
    public final String getName() { return "ParcelBuy"; }
	@Nonnull
    @Sequence(0)
	public ParcelBuy_bAgentData bagentdata=new ParcelBuy_bAgentData();
	@Nonnull
    @Sequence(1)
	public ParcelBuy_bData bdata=new ParcelBuy_bData();
	@Nonnull
    @Sequence(2)
	public ParcelBuy_bParcelData bparceldata=new ParcelBuy_bParcelData();
	public ParcelBuy(){}
	public ParcelBuy(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
