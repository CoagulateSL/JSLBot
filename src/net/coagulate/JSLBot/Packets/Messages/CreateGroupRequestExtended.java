package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class CreateGroupRequestExtended extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 429; }
	@Nonnull
    public final String getName() { return "CreateGroupRequestExtended"; }
	@Nonnull
    @Sequence(0)
	public CreateGroupRequestExtended_bAgentData bagentdata=new CreateGroupRequestExtended_bAgentData();
	@Nonnull
    @Sequence(1)
	public CreateGroupRequestExtended_bGroupData bgroupdata=new CreateGroupRequestExtended_bGroupData();
	public CreateGroupRequestExtended(){}
	public CreateGroupRequestExtended(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
