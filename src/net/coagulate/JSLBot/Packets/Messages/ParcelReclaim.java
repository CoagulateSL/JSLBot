package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ParcelReclaim extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 208; }
	@Nonnull
    public final String getName() { return "ParcelReclaim"; }
	@Nonnull
    @Sequence(0)
	public ParcelReclaim_bAgentData bagentdata=new ParcelReclaim_bAgentData();
	@Nonnull
    @Sequence(1)
	public ParcelReclaim_bData bdata=new ParcelReclaim_bData();
	public ParcelReclaim(){}
	public ParcelReclaim(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
