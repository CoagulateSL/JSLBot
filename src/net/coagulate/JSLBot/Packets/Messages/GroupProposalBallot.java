package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupProposalBallot extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 364; }
	public final String getName() { return "GroupProposalBallot"; }
	@Sequence(0)
	public GroupProposalBallot_bAgentData bagentdata=new GroupProposalBallot_bAgentData();
	@Sequence(1)
	public GroupProposalBallot_bProposalData bproposaldata=new GroupProposalBallot_bProposalData();
}
