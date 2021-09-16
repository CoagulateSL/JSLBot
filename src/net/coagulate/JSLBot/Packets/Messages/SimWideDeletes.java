package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class SimWideDeletes extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 129; }
	@Nonnull
    public final String getName() { return "SimWideDeletes"; }
	@Nonnull
    @Sequence(0)
	public SimWideDeletes_bAgentData bagentdata=new SimWideDeletes_bAgentData();
	@Nonnull
    @Sequence(1)
	public SimWideDeletes_bDataBlock bdatablock=new SimWideDeletes_bDataBlock();
	public SimWideDeletes(){}
	public SimWideDeletes(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
