package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelAccessListReply_bData extends Block {
	@Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Sequence(1)
	public S32 vsequenceid=new S32();
	@Sequence(2)
	public U32 vflags=new U32();
	@Sequence(3)
	public S32 vlocalid=new S32();
}
