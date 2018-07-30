package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelAccessListReply_bList extends Block {
	@Sequence(0)
	public LLUUID vid=new LLUUID();
	@Sequence(1)
	public S32 vtime=new S32();
	@Sequence(2)
	public U32 vflags=new U32();
}
