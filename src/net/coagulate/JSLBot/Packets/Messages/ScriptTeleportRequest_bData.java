package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLVector3;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class ScriptTeleportRequest_bData extends Block {
	@Nonnull
    @Sequence(0)
	public Variable1 vobjectname=new Variable1();
	@Nonnull
    @Sequence(1)
	public Variable1 vsimname=new Variable1();
	@Nonnull
    @Sequence(2)
	public LLVector3 vsimposition=new LLVector3();
	@Nonnull
    @Sequence(3)
	public LLVector3 vlookat=new LLVector3();
}
