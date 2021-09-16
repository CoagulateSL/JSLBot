package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class RpcChannelRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 413; }
	@Nonnull
    public final String getName() { return "RpcChannelRequest"; }
	@Nonnull
    @Sequence(0)
	public RpcChannelRequest_bDataBlock bdatablock=new RpcChannelRequest_bDataBlock();
}
