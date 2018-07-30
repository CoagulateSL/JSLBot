package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SimStats_bRegion extends Block {
	@Sequence(0)
	public U32 vregionx=new U32();
	@Sequence(1)
	public U32 vregiony=new U32();
	@Sequence(2)
	public U32 vregionflags=new U32();
	@Sequence(3)
	public U32 vobjectcapacity=new U32();
}
