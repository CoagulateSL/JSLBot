package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RequestMultipleObjects_bObjectData extends Block {
	@Sequence(0)
	public U8 vcachemisstype=new U8();
	@Sequence(1)
	public U32 vid=new U32();
}
