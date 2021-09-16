package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.S32;

import javax.annotation.Nonnull;

public class ParcelObjectOwnersReply_bData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vownerid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public BOOL visgroupowned=new BOOL();
	@Nonnull
    @Sequence(2)
	public S32 vcount=new S32();
	@Nonnull
    @Sequence(3)
	public BOOL vonlinestatus=new BOOL();
}
