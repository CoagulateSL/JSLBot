package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RpcScriptRequestInboundForward_bDataBlock extends Block {
	@Sequence(0)
	public IPADDR vrpcserverip=new IPADDR();
	@Sequence(1)
	public IPPORT vrpcserverport=new IPPORT();
	@Sequence(2)
	public LLUUID vtaskid=new LLUUID();
	@Sequence(3)
	public LLUUID vitemid=new LLUUID();
	@Sequence(4)
	public LLUUID vchannelid=new LLUUID();
	@Sequence(5)
	public U32 vintvalue=new U32();
	@Sequence(6)
	public Variable2 vstringvalue=new Variable2();
}
