package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ParcelGodMarkAsContent extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 227; }
	@Nonnull
    public final String getName() { return "ParcelGodMarkAsContent"; }
	@Nonnull
    @Sequence(0)
	public ParcelGodMarkAsContent_bAgentData bagentdata=new ParcelGodMarkAsContent_bAgentData();
	@Nonnull
    @Sequence(1)
	public ParcelGodMarkAsContent_bParcelData bparceldata=new ParcelGodMarkAsContent_bParcelData();
	public ParcelGodMarkAsContent(){}
	public ParcelGodMarkAsContent(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
