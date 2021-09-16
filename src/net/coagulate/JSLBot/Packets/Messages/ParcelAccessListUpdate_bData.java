package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.S32;
import net.coagulate.JSLBot.Packets.Types.U32;

import javax.annotation.Nonnull;

public class ParcelAccessListUpdate_bData extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vflags=new U32();
	@Nonnull
    @Sequence(1)
	public S32 vlocalid=new S32();
	@Nonnull
    @Sequence(2)
	public LLUUID vtransactionid=new LLUUID();
	@Nonnull
    @Sequence(3)
	public S32 vsequenceid=new S32();
	@Nonnull
    @Sequence(4)
	public S32 vsections=new S32();
}
