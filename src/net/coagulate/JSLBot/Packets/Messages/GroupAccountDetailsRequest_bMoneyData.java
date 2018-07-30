package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupAccountDetailsRequest_bMoneyData extends Block {
	@Sequence(0)
	public LLUUID vrequestid=new LLUUID();
	@Sequence(1)
	public S32 vintervaldays=new S32();
	@Sequence(2)
	public S32 vcurrentinterval=new S32();
}
