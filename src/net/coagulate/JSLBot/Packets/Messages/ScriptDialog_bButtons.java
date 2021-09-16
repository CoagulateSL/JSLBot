package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.Variable1;

import javax.annotation.Nonnull;

public class ScriptDialog_bButtons extends Block {
	@Nonnull
    @Sequence(0)
	public Variable1 vbuttonlabel=new Variable1();
}
