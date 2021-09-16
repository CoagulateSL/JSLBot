package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class GroupProposalBallot extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 364; }
	@Nonnull
    public final String getName() { return "GroupProposalBallot"; }
	@Nonnull
    @Sequence(0)
	public GroupProposalBallot_bAgentData bagentdata=new GroupProposalBallot_bAgentData();
	@Nonnull
    @Sequence(1)
	public GroupProposalBallot_bProposalData bproposaldata=new GroupProposalBallot_bProposalData();
	public GroupProposalBallot(){}
	public GroupProposalBallot(@Nonnull JSLBot bot) {
		bagentdata.vsessionid=bot.getSession();
		bagentdata.vagentid=bot.getUUID();
	}
}
