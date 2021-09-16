package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class RpcChannelReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 414; }
	@Nonnull
    public final String getName() { return "RpcChannelReply"; }
	@Nonnull
    @Sequence(0)
	public RpcChannelReply_bDataBlock bdatablock=new RpcChannelReply_bDataBlock();
}
