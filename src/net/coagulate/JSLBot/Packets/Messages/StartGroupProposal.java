package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class StartGroupProposal extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 363; }
	@Nonnull
    public final String getName() { return "StartGroupProposal"; }
	@Nonnull
    @Sequence(0)
	public StartGroupProposal_bAgentData bagentdata=new StartGroupProposal_bAgentData();
	@Nonnull
    @Sequence(1)
	public StartGroupProposal_bProposalData bproposaldata=new StartGroupProposal_bProposalData();
	public StartGroupProposal(){}
	public StartGroupProposal(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
