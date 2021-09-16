package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ConfirmEnableSimulator extends Block implements Message {
	public final int getFrequency() { return Frequency.MEDIUM; }
	public final int getId() { return 8; }
	@Nonnull
    public final String getName() { return "ConfirmEnableSimulator"; }
	@Nonnull
    @Sequence(0)
	public ConfirmEnableSimulator_bAgentData bagentdata=new ConfirmEnableSimulator_bAgentData();
	public ConfirmEnableSimulator(){}
	public ConfirmEnableSimulator(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
