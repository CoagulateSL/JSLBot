package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.S8;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class LinkInventoryItem_bInventoryBlock extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vcallbackid=new U32();
	@Nonnull
    @Sequence(1)
	public LLUUID vfolderid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public LLUUID vtransactionid=new LLUUID();
	@Nonnull
    @Sequence(3)
	public LLUUID volditemid=new LLUUID();
	@Nonnull
    @Sequence(4)
	public S8 vtype=new S8();
	@Nonnull
    @Sequence(5)
	public S8 vinvtype=new S8();
	@Nonnull
    @Sequence(6)
	public Variable1 vname=new Variable1();
	@Nonnull
    @Sequence(7)
	public Variable1 vdescription=new Variable1();
}
