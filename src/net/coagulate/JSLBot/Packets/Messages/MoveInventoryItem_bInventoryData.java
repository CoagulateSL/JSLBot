package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class MoveInventoryItem_bInventoryData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vitemid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vfolderid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public Variable1 vnewname=new Variable1();
}
