package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ParcelReturnObjects extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 199; }
	@Nonnull
    public final String getName() { return "ParcelReturnObjects"; }
	@Nonnull
    @Sequence(0)
	public ParcelReturnObjects_bAgentData bagentdata=new ParcelReturnObjects_bAgentData();
	@Nonnull
    @Sequence(1)
	public ParcelReturnObjects_bParcelData bparceldata=new ParcelReturnObjects_bParcelData();
	@Sequence(2)
	public List<ParcelReturnObjects_bTaskIDs> btaskids;
	@Sequence(3)
	public List<ParcelReturnObjects_bOwnerIDs> bownerids;
	public ParcelReturnObjects(){}
	public ParcelReturnObjects(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
