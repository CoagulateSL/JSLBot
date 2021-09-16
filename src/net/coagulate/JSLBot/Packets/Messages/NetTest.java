package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class NetTest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 326; }
	@Nonnull
    public final String getName() { return "NetTest"; }
	@Nonnull
    @Sequence(0)
	public NetTest_bNetBlock bnetblock=new NetTest_bNetBlock();
}
