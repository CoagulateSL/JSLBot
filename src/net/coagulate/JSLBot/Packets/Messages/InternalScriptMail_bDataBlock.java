package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.Variable1;
import net.coagulate.JSLBot.Packets.Types.Variable2;

import javax.annotation.Nonnull;

public class InternalScriptMail_bDataBlock extends Block {
	@Nonnull
    @Sequence(0)
	public Variable1 vfrom=new Variable1();
	@Nonnull
    @Sequence(1)
	public LLUUID vto=new LLUUID();
	@Nonnull
    @Sequence(2)
	public Variable1 vsubject=new Variable1();
	@Nonnull
    @Sequence(3)
	public Variable2 vbody=new Variable2();
}
