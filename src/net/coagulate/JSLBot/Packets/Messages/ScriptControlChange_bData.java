package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;
import net.coagulate.JSLBot.Packets.Types.U32;

import javax.annotation.Nonnull;

public class ScriptControlChange_bData extends Block {
	@Nonnull
    @Sequence(0)
	public BOOL vtakecontrols=new BOOL();
	@Nonnull
    @Sequence(1)
	public U32 vcontrols=new U32();
	@Nonnull
    @Sequence(2)
	public BOOL vpasstoagent=new BOOL();
}
