package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class TerminateFriendship extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 300; }
	@Nonnull
    public final String getName() { return "TerminateFriendship"; }
	@Nonnull
    @Sequence(0)
	public TerminateFriendship_bAgentData bagentdata=new TerminateFriendship_bAgentData();
	@Nonnull
    @Sequence(1)
	public TerminateFriendship_bExBlock bexblock=new TerminateFriendship_bExBlock();
	public TerminateFriendship(){}
	public TerminateFriendship(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
