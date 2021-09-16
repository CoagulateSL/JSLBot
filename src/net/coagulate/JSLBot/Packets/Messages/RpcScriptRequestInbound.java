package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class RpcScriptRequestInbound extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 415; }
	@Nonnull
    public final String getName() { return "RpcScriptRequestInbound"; }
	@Nonnull
    @Sequence(0)
	public RpcScriptRequestInbound_bTargetBlock btargetblock=new RpcScriptRequestInbound_bTargetBlock();
	@Nonnull
    @Sequence(1)
	public RpcScriptRequestInbound_bDataBlock bdatablock=new RpcScriptRequestInbound_bDataBlock();
}
