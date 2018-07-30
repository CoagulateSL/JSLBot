package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MapItemRequest_bRequestData extends Block {
	@Sequence(0)
	public U32 vitemtype=new U32();
	@Sequence(1)
	public U64 vregionhandle=new U64();
}
