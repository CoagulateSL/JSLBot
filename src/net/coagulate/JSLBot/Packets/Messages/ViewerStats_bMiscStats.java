package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ViewerStats_bMiscStats extends Block {
	@Sequence(0)
	public U32 vtype=new U32();
	@Sequence(1)
	public F64 vvalue=new F64();
}
