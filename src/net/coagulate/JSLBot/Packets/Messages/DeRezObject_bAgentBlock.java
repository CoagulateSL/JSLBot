package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DeRezObject_bAgentBlock extends Block {
	@Sequence(0)
	public LLUUID vgroupid=new LLUUID();
	@Sequence(1)
	public U8 vdestination=new U8();
	@Sequence(2)
	public LLUUID vdestinationid=new LLUUID();
	@Sequence(3)
	public LLUUID vtransactionid=new LLUUID();
	@Sequence(4)
	public U8 vpacketcount=new U8();
	@Sequence(5)
	public U8 vpacketnumber=new U8();
}
