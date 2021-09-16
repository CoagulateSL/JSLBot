package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ParcelBuyPass extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 206; }
	@Nonnull
    public final String getName() { return "ParcelBuyPass"; }
	@Nonnull
    @Sequence(0)
	public ParcelBuyPass_bAgentData bagentdata=new ParcelBuyPass_bAgentData();
	@Nonnull
    @Sequence(1)
	public ParcelBuyPass_bParcelData bparceldata=new ParcelBuyPass_bParcelData();
	public ParcelBuyPass(){}
	public ParcelBuyPass(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
