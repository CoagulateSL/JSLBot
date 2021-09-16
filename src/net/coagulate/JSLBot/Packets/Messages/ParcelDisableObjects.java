package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ParcelDisableObjects extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 201; }
	@Nonnull
    public final String getName() { return "ParcelDisableObjects"; }
	@Nonnull
    @Sequence(0)
	public ParcelDisableObjects_bAgentData bagentdata=new ParcelDisableObjects_bAgentData();
	@Nonnull
    @Sequence(1)
	public ParcelDisableObjects_bParcelData bparceldata=new ParcelDisableObjects_bParcelData();
	@Sequence(2)
	public List<ParcelDisableObjects_bTaskIDs> btaskids;
	@Sequence(3)
	public List<ParcelDisableObjects_bOwnerIDs> bownerids;
	public ParcelDisableObjects(){}
	public ParcelDisableObjects(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
