package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelObjectOwnersReply_bData extends Block {
	@Sequence(0)
	public LLUUID vownerid=new LLUUID();
	@Sequence(1)
	public BOOL visgroupowned=new BOOL();
	@Sequence(2)
	public S32 vcount=new S32();
	@Sequence(3)
	public BOOL vonlinestatus=new BOOL();
}
