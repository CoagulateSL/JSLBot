package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class JoinGroupRequestExtended extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 428; }
	@Nonnull
    public final String getName() { return "JoinGroupRequestExtended"; }
	@Nonnull
    @Sequence(0)
	public JoinGroupRequestExtended_bAgentData bagentdata=new JoinGroupRequestExtended_bAgentData();
	@Nonnull
    @Sequence(1)
	public JoinGroupRequestExtended_bGroupData bgroupdata=new JoinGroupRequestExtended_bGroupData();
	public JoinGroupRequestExtended(){}
	public JoinGroupRequestExtended(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
