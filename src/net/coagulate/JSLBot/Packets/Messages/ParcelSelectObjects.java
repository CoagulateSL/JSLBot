package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ParcelSelectObjects extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 202; }
	@Nonnull
    public final String getName() { return "ParcelSelectObjects"; }
	@Nonnull
    @Sequence(0)
	public ParcelSelectObjects_bAgentData bagentdata=new ParcelSelectObjects_bAgentData();
	@Nonnull
    @Sequence(1)
	public ParcelSelectObjects_bParcelData bparceldata=new ParcelSelectObjects_bParcelData();
	@Sequence(2)
	public List<ParcelSelectObjects_bReturnIDs> breturnids;
	public ParcelSelectObjects(){}
	public ParcelSelectObjects(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
