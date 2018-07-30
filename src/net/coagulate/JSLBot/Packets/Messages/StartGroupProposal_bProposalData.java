package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class StartGroupProposal_bProposalData extends Block {
	@Sequence(0)
	public LLUUID vgroupid=new LLUUID();
	@Sequence(1)
	public S32 vquorum=new S32();
	@Sequence(2)
	public F32 vmajority=new F32();
	@Sequence(3)
	public S32 vduration=new S32();
	@Sequence(4)
	public Variable1 vproposaltext=new Variable1();
}
