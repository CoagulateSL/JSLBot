package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MoneyBalanceReply_bTransactionInfo extends Block {
	@Sequence(0)
	public S32 vtransactiontype=new S32();
	@Sequence(1)
	public LLUUID vsourceid=new LLUUID();
	@Sequence(2)
	public BOOL vissourcegroup=new BOOL();
	@Sequence(3)
	public LLUUID vdestid=new LLUUID();
	@Sequence(4)
	public BOOL visdestgroup=new BOOL();
	@Sequence(5)
	public S32 vamount=new S32();
	@Sequence(6)
	public Variable1 vitemdescription=new Variable1();
}
