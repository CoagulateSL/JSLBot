package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class RpcScriptRequestInboundForward_bDataBlock extends Block {
	@Nonnull
    @Sequence(0)
	public IPADDR vrpcserverip=new IPADDR();
	@Nonnull
    @Sequence(1)
	public IPPORT vrpcserverport=new IPPORT();
	@Nonnull
    @Sequence(2)
	public LLUUID vtaskid=new LLUUID();
	@Nonnull
    @Sequence(3)
	public LLUUID vitemid=new LLUUID();
	@Nonnull
    @Sequence(4)
	public LLUUID vchannelid=new LLUUID();
	@Nonnull
    @Sequence(5)
	public U32 vintvalue=new U32();
	@Nonnull
    @Sequence(6)
	public Variable2 vstringvalue=new Variable2();
}
