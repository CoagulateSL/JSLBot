package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class TeleportFinish_bInfo extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public U32 vlocationid=new U32();
	@Nonnull
    @Sequence(2)
	public IPADDR vsimip=new IPADDR();
	@Nonnull
    @Sequence(3)
	public IPPORT vsimport=new IPPORT();
	@Nonnull
    @Sequence(4)
	public U64 vregionhandle=new U64();
	@Nonnull
    @Sequence(5)
	public Variable2 vseedcapability=new Variable2();
	@Nonnull
    @Sequence(6)
	public U8 vsimaccess=new U8();
	@Nonnull
    @Sequence(7)
	public U32 vteleportflags=new U32();
}
