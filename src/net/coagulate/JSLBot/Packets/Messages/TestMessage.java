package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class TestMessage extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 1; }
	@Nonnull
    public final String getName() { return "TestMessage"; }
	@Nonnull
    @Sequence(0)
	public TestMessage_bTestBlock1 btestblock1=new TestMessage_bTestBlock1();
	@Nonnull
    @Sequence(1)
	public TestMessage_bNeighborBlock bneighborblock[]=new TestMessage_bNeighborBlock[4];
}
