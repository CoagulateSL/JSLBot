package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class NeighborList_bNeighborBlock extends Block {
	@Nonnull
    @Sequence(0)
	public IPADDR vip=new IPADDR();
	@Nonnull
    @Sequence(1)
	public IPPORT vport=new IPPORT();
	@Nonnull
    @Sequence(2)
	public IPADDR vpublicip=new IPADDR();
	@Nonnull
    @Sequence(3)
	public IPPORT vpublicport=new IPPORT();
	@Nonnull
    @Sequence(4)
	public LLUUID vregionid=new LLUUID();
	@Nonnull
    @Sequence(5)
	public Variable1 vname=new Variable1();
	@Nonnull
    @Sequence(6)
	public U8 vsimaccess=new U8();
}
