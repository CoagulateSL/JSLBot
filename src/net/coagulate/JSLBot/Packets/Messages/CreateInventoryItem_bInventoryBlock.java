package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class CreateInventoryItem_bInventoryBlock extends Block {
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
	public U32 vnextownermask=new U32();
	@Nonnull
    @Sequence(4)
	public S8 vtype=new S8();
	@Nonnull
    @Sequence(5)
	public S8 vinvtype=new S8();
	@Nonnull
    @Sequence(6)
	public U8 vwearabletype=new U8();
	@Nonnull
    @Sequence(7)
	public Variable1 vname=new Variable1();
	@Nonnull
    @Sequence(8)
	public Variable1 vdescription=new Variable1();
}
