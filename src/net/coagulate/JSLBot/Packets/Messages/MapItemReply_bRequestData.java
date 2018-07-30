package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MapItemReply_bRequestData extends Block {
	@Sequence(0)
	public U32 vitemtype=new U32();
}
