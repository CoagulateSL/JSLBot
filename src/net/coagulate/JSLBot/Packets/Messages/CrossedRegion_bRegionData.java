package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.IPADDR;
import net.coagulate.JSLBot.Packets.Types.IPPORT;
import net.coagulate.JSLBot.Packets.Types.U64;
import net.coagulate.JSLBot.Packets.Types.Variable2;

import javax.annotation.Nonnull;

public class CrossedRegion_bRegionData extends Block {
	@Nonnull
    @Sequence(0)
	public IPADDR vsimip=new IPADDR();
	@Nonnull
    @Sequence(1)
	public IPPORT vsimport=new IPPORT();
	@Nonnull
    @Sequence(2)
	public U64 vregionhandle=new U64();
	@Nonnull
    @Sequence(3)
	public Variable2 vseedcapability=new Variable2();
}
