package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class EdgeDataPacket_bEdgeData extends Block {
	@Sequence(0)
	public U8 vlayertype=new U8();
	@Sequence(1)
	public U8 vdirection=new U8();
	@Sequence(2)
	public Variable2 vlayerdata=new Variable2();
}
