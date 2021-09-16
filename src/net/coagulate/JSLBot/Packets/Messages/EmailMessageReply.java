package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class EmailMessageReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 336; }
	@Nonnull
    public final String getName() { return "EmailMessageReply"; }
	@Nonnull
    @Sequence(0)
	public EmailMessageReply_bDataBlock bdatablock=new EmailMessageReply_bDataBlock();
}
