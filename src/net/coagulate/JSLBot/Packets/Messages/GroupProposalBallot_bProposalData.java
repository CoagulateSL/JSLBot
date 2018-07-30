package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupProposalBallot_bProposalData extends Block {
	@Sequence(0)
	public LLUUID vproposalid=new LLUUID();
	@Sequence(1)
	public LLUUID vgroupid=new LLUUID();
	@Sequence(2)
	public Variable1 vvotecast=new Variable1();
}
