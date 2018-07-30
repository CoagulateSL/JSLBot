package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RequestParcelTransfer_bRegionData extends Block {
	@Sequence(0)
	public LLUUID vregionid=new LLUUID();
	@Sequence(1)
	public U32 vgridx=new U32();
	@Sequence(2)
	public U32 vgridy=new U32();
}
