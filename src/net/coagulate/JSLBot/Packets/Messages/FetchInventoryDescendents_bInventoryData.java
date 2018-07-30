package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class FetchInventoryDescendents_bInventoryData extends Block {
	@Sequence(0)
	public LLUUID vfolderid=new LLUUID();
	@Sequence(1)
	public LLUUID vownerid=new LLUUID();
	@Sequence(2)
	public S32 vsortorder=new S32();
	@Sequence(3)
	public BOOL vfetchfolders=new BOOL();
	@Sequence(4)
	public BOOL vfetchitems=new BOOL();
}
