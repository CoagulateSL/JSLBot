package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class LogFailedMoneyTransaction_bTransactionData extends Block {
	@Sequence(0)
	public LLUUID vtransactionid=new LLUUID();
	@Sequence(1)
	public U32 vtransactiontime=new U32();
	@Sequence(2)
	public S32 vtransactiontype=new S32();
	@Sequence(3)
	public LLUUID vsourceid=new LLUUID();
	@Sequence(4)
	public LLUUID vdestid=new LLUUID();
	@Sequence(5)
	public U8 vflags=new U8();
	@Sequence(6)
	public S32 vamount=new S32();
	@Sequence(7)
	public IPADDR vsimulatorip=new IPADDR();
	@Sequence(8)
	public U32 vgridx=new U32();
	@Sequence(9)
	public U32 vgridy=new U32();
	@Sequence(10)
	public U8 vfailuretype=new U8();
}
