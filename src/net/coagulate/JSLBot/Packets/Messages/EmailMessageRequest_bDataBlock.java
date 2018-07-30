package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class EmailMessageRequest_bDataBlock extends Block {
	@Sequence(0)
	public LLUUID vobjectid=new LLUUID();
	@Sequence(1)
	public Variable1 vfromaddress=new Variable1();
	@Sequence(2)
	public Variable1 vsubject=new Variable1();
}
