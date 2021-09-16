package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class RequestPayPrice extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 161; }
	@Nonnull
    public final String getName() { return "RequestPayPrice"; }
	@Nonnull
    @Sequence(0)
	public RequestPayPrice_bObjectData bobjectdata=new RequestPayPrice_bObjectData();
}
