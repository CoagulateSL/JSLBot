package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class EventLocationReply_bEventData extends Block {
	@Sequence(0)
	public BOOL vsuccess=new BOOL();
	@Sequence(1)
	public LLUUID vregionid=new LLUUID();
	@Sequence(2)
	public LLVector3 vregionpos=new LLVector3();
}
