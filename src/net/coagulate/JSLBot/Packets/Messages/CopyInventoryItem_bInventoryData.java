package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class CopyInventoryItem_bInventoryData extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vcallbackid=new U32();
	@Nonnull
    @Sequence(1)
	public LLUUID voldagentid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public LLUUID volditemid=new LLUUID();
	@Nonnull
    @Sequence(3)
	public LLUUID vnewfolderid=new LLUUID();
	@Nonnull
    @Sequence(4)
	public Variable1 vnewname=new Variable1();
}
