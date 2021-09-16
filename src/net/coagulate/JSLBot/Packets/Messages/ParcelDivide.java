package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ParcelDivide extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 211; }
	@Nonnull
    public final String getName() { return "ParcelDivide"; }
	@Nonnull
    @Sequence(0)
	public ParcelDivide_bAgentData bagentdata=new ParcelDivide_bAgentData();
	@Nonnull
    @Sequence(1)
	public ParcelDivide_bParcelData bparceldata=new ParcelDivide_bParcelData();
	public ParcelDivide(){}
	public ParcelDivide(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
