package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class RpcScriptRequestInboundForward extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 416; }
	@Nonnull
    public final String getName() { return "RpcScriptRequestInboundForward"; }
	@Nonnull
    @Sequence(0)
	public RpcScriptRequestInboundForward_bDataBlock bdatablock=new RpcScriptRequestInboundForward_bDataBlock();
}
