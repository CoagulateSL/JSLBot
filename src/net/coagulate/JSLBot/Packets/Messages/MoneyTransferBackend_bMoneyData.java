package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MoneyTransferBackend_bMoneyData extends Block {
	@Sequence(0)
	public LLUUID vtransactionid=new LLUUID();
	@Sequence(1)
	public U32 vtransactiontime=new U32();
	@Sequence(2)
	public LLUUID vsourceid=new LLUUID();
	@Sequence(3)
	public LLUUID vdestid=new LLUUID();
	@Sequence(4)
	public U8 vflags=new U8();
	@Sequence(5)
	public S32 vamount=new S32();
	@Sequence(6)
	public U8 vaggregatepermnextowner=new U8();
	@Sequence(7)
	public U8 vaggregateperminventory=new U8();
	@Sequence(8)
	public S32 vtransactiontype=new S32();
	@Sequence(9)
	public LLUUID vregionid=new LLUUID();
	@Sequence(10)
	public U32 vgridx=new U32();
	@Sequence(11)
	public U32 vgridy=new U32();
	@Sequence(12)
	public Variable1 vdescription=new Variable1();
}
