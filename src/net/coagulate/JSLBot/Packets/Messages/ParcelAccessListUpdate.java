package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ParcelAccessListUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 217; }
	@Nonnull
    public final String getName() { return "ParcelAccessListUpdate"; }
	@Nonnull
    @Sequence(0)
	public ParcelAccessListUpdate_bAgentData bagentdata=new ParcelAccessListUpdate_bAgentData();
	@Nonnull
    @Sequence(1)
	public ParcelAccessListUpdate_bData bdata=new ParcelAccessListUpdate_bData();
	@Sequence(2)
	public List<ParcelAccessListUpdate_bList> blist;
	public ParcelAccessListUpdate(){}
	public ParcelAccessListUpdate(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
