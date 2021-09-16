package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLVector3;
import net.coagulate.JSLBot.Packets.Types.U32;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class SetStartLocationRequest_bStartLocationData extends Block {
	@Nonnull
    @Sequence(0)
	public Variable1 vsimname=new Variable1();
	@Nonnull
    @Sequence(1)
	public U32 vlocationid=new U32();
	@Nonnull
    @Sequence(2)
	public LLVector3 vlocationpos=new LLVector3();
	@Nonnull
    @Sequence(3)
	public LLVector3 vlocationlookat=new LLVector3();
}
