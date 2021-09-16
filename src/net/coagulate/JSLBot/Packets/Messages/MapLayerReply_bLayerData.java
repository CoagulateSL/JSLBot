package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U32;

import javax.annotation.Nonnull;

public class MapLayerReply_bLayerData extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vleft=new U32();
	@Nonnull
    @Sequence(1)
	public U32 vright=new U32();
	@Nonnull
    @Sequence(2)
	public U32 vtop=new U32();
	@Nonnull
    @Sequence(3)
	public U32 vbottom=new U32();
	@Nonnull
    @Sequence(4)
	public LLUUID vimageid=new LLUUID();
}
