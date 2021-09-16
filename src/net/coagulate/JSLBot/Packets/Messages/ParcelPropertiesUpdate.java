package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ParcelPropertiesUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 198; }
	@Nonnull
    public final String getName() { return "ParcelPropertiesUpdate"; }
	@Nonnull
    @Sequence(0)
	public ParcelPropertiesUpdate_bAgentData bagentdata=new ParcelPropertiesUpdate_bAgentData();
	@Nonnull
    @Sequence(1)
	public ParcelPropertiesUpdate_bParcelData bparceldata=new ParcelPropertiesUpdate_bParcelData();
	public ParcelPropertiesUpdate(){}
	public ParcelPropertiesUpdate(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
