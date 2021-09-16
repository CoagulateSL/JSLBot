package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class EstateCovenantReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 204; }
	@Nonnull
    public final String getName() { return "EstateCovenantReply"; }
	@Nonnull
    @Sequence(0)
	public EstateCovenantReply_bData bdata=new EstateCovenantReply_bData();
}
