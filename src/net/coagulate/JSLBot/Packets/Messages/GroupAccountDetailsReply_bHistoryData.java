package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupAccountDetailsReply_bHistoryData extends Block {
	@Sequence(0)
	public Variable1 vdescription=new Variable1();
	@Sequence(1)
	public S32 vamount=new S32();
}
