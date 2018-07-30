package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ViewerStats_bFailStats extends Block {
	@Sequence(0)
	public U32 vsendpacket=new U32();
	@Sequence(1)
	public U32 vdropped=new U32();
	@Sequence(2)
	public U32 vresent=new U32();
	@Sequence(3)
	public U32 vfailedresends=new U32();
	@Sequence(4)
	public U32 voffcircuit=new U32();
	@Sequence(5)
	public U32 vinvalid=new U32();
}
