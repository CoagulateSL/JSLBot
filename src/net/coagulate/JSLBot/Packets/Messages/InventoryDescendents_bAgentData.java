package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.S32;

import javax.annotation.Nonnull;

public class InventoryDescendents_bAgentData extends Block {
	@Sequence(0)
	public LLUUID vagentid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vfolderid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public LLUUID vownerid=new LLUUID();
	@Nonnull
    @Sequence(3)
	public S32 vversion=new S32();
	@Nonnull
    @Sequence(4)
	public S32 vdescendents=new S32();
}
