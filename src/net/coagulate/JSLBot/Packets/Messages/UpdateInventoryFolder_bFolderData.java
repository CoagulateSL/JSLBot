package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.S8;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class UpdateInventoryFolder_bFolderData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vfolderid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vparentid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public S8 vtype=new S8();
	@Nonnull
    @Sequence(3)
	public Variable1 vname=new Variable1();
}
