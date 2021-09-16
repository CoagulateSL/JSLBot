package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ParcelGodForceOwner extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 214; }
	@Nonnull
    public final String getName() { return "ParcelGodForceOwner"; }
	@Nonnull
    @Sequence(0)
	public ParcelGodForceOwner_bAgentData bagentdata=new ParcelGodForceOwner_bAgentData();
	@Nonnull
    @Sequence(1)
	public ParcelGodForceOwner_bData bdata=new ParcelGodForceOwner_bData();
	public ParcelGodForceOwner(){}
	public ParcelGodForceOwner(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
