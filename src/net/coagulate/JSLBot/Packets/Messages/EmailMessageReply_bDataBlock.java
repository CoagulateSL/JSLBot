package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class EmailMessageReply_bDataBlock extends Block {
	@Sequence(0)
	public LLUUID vobjectid=new LLUUID();
	@Sequence(1)
	public U32 vmore=new U32();
	@Sequence(2)
	public U32 vtime=new U32();
	@Sequence(3)
	public Variable1 vfromaddress=new Variable1();
	@Sequence(4)
	public Variable1 vsubject=new Variable1();
	@Sequence(5)
	public Variable2 vdata=new Variable2();
	@Sequence(6)
	public Variable1 vmailfilter=new Variable1();
}
