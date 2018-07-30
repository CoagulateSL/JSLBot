package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupActiveProposalItemReply_bProposalData extends Block {
	@Sequence(0)
	public LLUUID vvoteid=new LLUUID();
	@Sequence(1)
	public LLUUID vvoteinitiator=new LLUUID();
	@Sequence(2)
	public Variable1 vtersedateid=new Variable1();
	@Sequence(3)
	public Variable1 vstartdatetime=new Variable1();
	@Sequence(4)
	public Variable1 venddatetime=new Variable1();
	@Sequence(5)
	public BOOL valreadyvoted=new BOOL();
	@Sequence(6)
	public Variable1 vvotecast=new Variable1();
	@Sequence(7)
	public F32 vmajority=new F32();
	@Sequence(8)
	public S32 vquorum=new S32();
	@Sequence(9)
	public Variable1 vproposaltext=new Variable1();
}
