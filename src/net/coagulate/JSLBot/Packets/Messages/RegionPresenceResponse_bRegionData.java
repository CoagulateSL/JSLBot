package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class RegionPresenceResponse_bRegionData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vregionid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public U64 vregionhandle=new U64();
	@Nonnull
    @Sequence(2)
	public IPADDR vinternalregionip=new IPADDR();
	@Nonnull
    @Sequence(3)
	public IPADDR vexternalregionip=new IPADDR();
	@Nonnull
    @Sequence(4)
	public IPPORT vregionport=new IPPORT();
	@Nonnull
    @Sequence(5)
	public F64 vvaliduntil=new F64();
	@Nonnull
    @Sequence(6)
	public Variable1 vmessage=new Variable1();
}
