package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.S32;
import net.coagulate.JSLBot.Packets.Types.U8;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class ParcelMediaUpdate_bDataBlockExtended extends Block {
	@Nonnull
    @Sequence(0)
	public Variable1 vmediatype=new Variable1();
	@Nonnull
    @Sequence(1)
	public Variable1 vmediadesc=new Variable1();
	@Nonnull
    @Sequence(2)
	public S32 vmediawidth=new S32();
	@Nonnull
    @Sequence(3)
	public S32 vmediaheight=new S32();
	@Nonnull
    @Sequence(4)
	public U8 vmedialoop=new U8();
}
