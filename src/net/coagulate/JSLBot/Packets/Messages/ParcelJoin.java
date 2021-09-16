package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ParcelJoin extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 210; }
	@Nonnull
    public final String getName() { return "ParcelJoin"; }
	@Nonnull
    @Sequence(0)
	public ParcelJoin_bAgentData bagentdata=new ParcelJoin_bAgentData();
	@Nonnull
    @Sequence(1)
	public ParcelJoin_bParcelData bparceldata=new ParcelJoin_bParcelData();
	public ParcelJoin(){}
	public ParcelJoin(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
