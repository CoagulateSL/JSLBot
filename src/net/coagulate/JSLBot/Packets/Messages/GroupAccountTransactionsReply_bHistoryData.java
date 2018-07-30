package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupAccountTransactionsReply_bHistoryData extends Block {
	@Sequence(0)
	public Variable1 vtime=new Variable1();
	@Sequence(1)
	public Variable1 vuser=new Variable1();
	@Sequence(2)
	public S32 vtype=new S32();
	@Sequence(3)
	public Variable1 vitem=new Variable1();
	@Sequence(4)
	public S32 vamount=new S32();
}
