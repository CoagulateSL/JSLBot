package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class OfferCallingCard_bAgentBlock extends Block {
	@Sequence(0)
	public LLUUID vdestid=new LLUUID();
	@Sequence(1)
	public LLUUID vtransactionid=new LLUUID();
}
