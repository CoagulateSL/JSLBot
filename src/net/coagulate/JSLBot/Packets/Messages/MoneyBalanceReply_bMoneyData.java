package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MoneyBalanceReply_bMoneyData extends Block {
	@Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Sequence(1)
	public LLUUID vtransactionid=new LLUUID();
	@Sequence(2)
	public BOOL vtransactionsuccess=new BOOL();
	@Sequence(3)
	public S32 vmoneybalance=new S32();
	@Sequence(4)
	public S32 vsquaremeterscredit=new S32();
	@Sequence(5)
	public S32 vsquaremeterscommitted=new S32();
	@Sequence(6)
	public Variable1 vdescription=new Variable1();
}
