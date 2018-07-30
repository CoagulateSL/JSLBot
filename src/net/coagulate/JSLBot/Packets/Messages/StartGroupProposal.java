package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class StartGroupProposal extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 363; }
	public final String getName() { return "StartGroupProposal"; }
	@Sequence(0)
	public StartGroupProposal_bAgentData bagentdata=new StartGroupProposal_bAgentData();
	@Sequence(1)
	public StartGroupProposal_bProposalData bproposaldata=new StartGroupProposal_bProposalData();
}
