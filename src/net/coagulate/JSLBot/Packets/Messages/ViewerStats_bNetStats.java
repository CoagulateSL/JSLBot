package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ViewerStats_bNetStats extends Block {
	@Sequence(0)
	public U32 vbytes=new U32();
	@Sequence(1)
	public U32 vpackets=new U32();
	@Sequence(2)
	public U32 vcompressed=new U32();
	@Sequence(3)
	public U32 vsavings=new U32();
}
