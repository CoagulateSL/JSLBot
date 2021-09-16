package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ParcelRelease extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 212; }
	@Nonnull
    public final String getName() { return "ParcelRelease"; }
	@Nonnull
    @Sequence(0)
	public ParcelRelease_bAgentData bagentdata=new ParcelRelease_bAgentData();
	@Nonnull
    @Sequence(1)
	public ParcelRelease_bData bdata=new ParcelRelease_bData();
	public ParcelRelease(){}
	public ParcelRelease(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
