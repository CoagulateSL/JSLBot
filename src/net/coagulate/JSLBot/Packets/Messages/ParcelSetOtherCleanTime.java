package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ParcelSetOtherCleanTime extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 200; }
	@Nonnull
    public final String getName() { return "ParcelSetOtherCleanTime"; }
	@Nonnull
    @Sequence(0)
	public ParcelSetOtherCleanTime_bAgentData bagentdata=new ParcelSetOtherCleanTime_bAgentData();
	@Nonnull
    @Sequence(1)
	public ParcelSetOtherCleanTime_bParcelData bparceldata=new ParcelSetOtherCleanTime_bParcelData();
	public ParcelSetOtherCleanTime(){}
	public ParcelSetOtherCleanTime(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
