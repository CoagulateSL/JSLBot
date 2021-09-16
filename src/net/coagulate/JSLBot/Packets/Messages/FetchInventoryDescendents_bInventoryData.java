package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.S32;

import javax.annotation.Nonnull;

public class FetchInventoryDescendents_bInventoryData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vfolderid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vownerid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public S32 vsortorder=new S32();
	@Nonnull
    @Sequence(3)
	public BOOL vfetchfolders=new BOOL();
	@Nonnull
    @Sequence(4)
	public BOOL vfetchitems=new BOOL();
}
