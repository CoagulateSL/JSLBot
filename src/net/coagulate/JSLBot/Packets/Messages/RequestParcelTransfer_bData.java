package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RequestParcelTransfer_bData extends Block {
	@Sequence(0)
	public LLUUID vtransactionid=new LLUUID();
	@Sequence(1)
	public U32 vtransactiontime=new U32();
	@Sequence(2)
	public LLUUID vsourceid=new LLUUID();
	@Sequence(3)
	public LLUUID vdestid=new LLUUID();
	@Sequence(4)
	public LLUUID vownerid=new LLUUID();
	@Sequence(5)
	public U8 vflags=new U8();
	@Sequence(6)
	public S32 vtransactiontype=new S32();
	@Sequence(7)
	public S32 vamount=new S32();
	@Sequence(8)
	public S32 vbillablearea=new S32();
	@Sequence(9)
	public S32 vactualarea=new S32();
	@Sequence(10)
	public BOOL vfinal=new BOOL();
}
