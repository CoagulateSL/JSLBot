package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;
import net.coagulate.JSLBot.Packets.Types.U16;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class ObjectExtraParams_bObjectData extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vobjectlocalid=new U32();
	@Nonnull
    @Sequence(1)
	public U16 vparamtype=new U16();
	@Nonnull
    @Sequence(2)
	public BOOL vparaminuse=new BOOL();
	@Nonnull
    @Sequence(3)
	public U32 vparamsize=new U32();
	@Nonnull
    @Sequence(4)
	public Variable1 vparamdata=new Variable1();
}
