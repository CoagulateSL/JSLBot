package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MoneyTransferRequest_bMoneyData extends Block {
	@Sequence(0)
	public LLUUID vsourceid=new LLUUID();
	@Sequence(1)
	public LLUUID vdestid=new LLUUID();
	@Sequence(2)
	public U8 vflags=new U8();
	@Sequence(3)
	public S32 vamount=new S32();
	@Sequence(4)
	public U8 vaggregatepermnextowner=new U8();
	@Sequence(5)
	public U8 vaggregateperminventory=new U8();
	@Sequence(6)
	public S32 vtransactiontype=new S32();
	@Sequence(7)
	public Variable1 vdescription=new Variable1();
}
