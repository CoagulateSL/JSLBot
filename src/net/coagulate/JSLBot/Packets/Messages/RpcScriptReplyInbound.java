package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class RpcScriptReplyInbound extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 417; }
	@Nonnull
    public final String getName() { return "RpcScriptReplyInbound"; }
	@Nonnull
    @Sequence(0)
	public RpcScriptReplyInbound_bDataBlock bdatablock=new RpcScriptReplyInbound_bDataBlock();
}
