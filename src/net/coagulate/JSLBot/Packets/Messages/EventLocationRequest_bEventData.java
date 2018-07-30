package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class EventLocationRequest_bEventData extends Block {
	@Sequence(0)
	public U32 veventid=new U32();
}
