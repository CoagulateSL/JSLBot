package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.S32;

import javax.annotation.Nonnull;

public class ParcelBuy_bData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vgroupid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public BOOL visgroupowned=new BOOL();
	@Nonnull
    @Sequence(2)
	public BOOL vremovecontribution=new BOOL();
	@Nonnull
    @Sequence(3)
	public S32 vlocalid=new S32();
	@Nonnull
    @Sequence(4)
	public BOOL vfinal=new BOOL();
}
