package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;

import javax.annotation.Nonnull;

public class SimStatus_bSimStatus extends Block {
	@Nonnull
    @Sequence(0)
	public BOOL vcanacceptagents=new BOOL();
	@Nonnull
    @Sequence(1)
	public BOOL vcanaccepttasks=new BOOL();
}
