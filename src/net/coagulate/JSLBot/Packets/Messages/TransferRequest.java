package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class TransferRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 153; }
	@Nonnull
    public final String getName() { return "TransferRequest"; }
	@Nonnull
    @Sequence(0)
	public TransferRequest_bTransferInfo btransferinfo=new TransferRequest_bTransferInfo();
}
