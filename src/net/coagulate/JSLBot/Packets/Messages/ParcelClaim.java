package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ParcelClaim extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 209; }
	@Nonnull
    public final String getName() { return "ParcelClaim"; }
	@Nonnull
    @Sequence(0)
	public ParcelClaim_bAgentData bagentdata=new ParcelClaim_bAgentData();
	@Nonnull
    @Sequence(1)
	public ParcelClaim_bData bdata=new ParcelClaim_bData();
	@Sequence(2)
	public List<ParcelClaim_bParcelData> bparceldata;
	public ParcelClaim(){}
	public ParcelClaim(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
