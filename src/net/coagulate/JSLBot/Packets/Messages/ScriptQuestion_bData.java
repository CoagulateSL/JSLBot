package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.S32;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class ScriptQuestion_bData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vtaskid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vitemid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public Variable1 vobjectname=new Variable1();
	@Nonnull
    @Sequence(3)
	public Variable1 vobjectowner=new Variable1();
	@Nonnull
    @Sequence(4)
	public S32 vquestions=new S32();
}
