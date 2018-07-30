package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ViewerStats_bDownloadTotals extends Block {
	@Sequence(0)
	public U32 vworld=new U32();
	@Sequence(1)
	public U32 vobjects=new U32();
	@Sequence(2)
	public U32 vtextures=new U32();
}
