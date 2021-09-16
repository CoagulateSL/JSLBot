package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.S32;
import net.coagulate.JSLBot.Packets.Types.Variable1;
import net.coagulate.JSLBot.Packets.Types.Variable2;

import javax.annotation.Nonnull;

public class Error_bData extends Block {
	@Nonnull
    @Sequence(0)
	public S32 vcode=new S32();
	@Nonnull
    @Sequence(1)
	public Variable1 vtoken=new Variable1();
	@Nonnull
    @Sequence(2)
	public LLUUID vid=new LLUUID();
	@Nonnull
    @Sequence(3)
	public Variable1 vsystem=new Variable1();
	@Nonnull
    @Sequence(4)
	public Variable2 vmessage=new Variable2();
	@Nonnull
    @Sequence(5)
	public Variable2 vdata=new Variable2();
}
