package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class StateSave extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 127; }
	@Nonnull
    public final String getName() { return "StateSave"; }
	@Nonnull
    @Sequence(0)
	public StateSave_bAgentData bagentdata=new StateSave_bAgentData();
	@Nonnull
    @Sequence(1)
	public StateSave_bDataBlock bdatablock=new StateSave_bDataBlock();
	public StateSave(){}
	public StateSave(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
