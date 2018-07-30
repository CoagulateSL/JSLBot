package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupAccountTransactionsReply_bMoneyData extends Block {
	@Sequence(0)
	public LLUUID vrequestid=new LLUUID();
	@Sequence(1)
	public S32 vintervaldays=new S32();
	@Sequence(2)
	public S32 vcurrentinterval=new S32();
	@Sequence(3)
	public Variable1 vstartdate=new Variable1();
}
