package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RpcScriptReplyInbound_bDataBlock extends Block {
	@Sequence(0)
	public LLUUID vtaskid=new LLUUID();
	@Sequence(1)
	public LLUUID vitemid=new LLUUID();
	@Sequence(2)
	public LLUUID vchannelid=new LLUUID();
	@Sequence(3)
	public U32 vintvalue=new U32();
	@Sequence(4)
	public Variable2 vstringvalue=new Variable2();
}
