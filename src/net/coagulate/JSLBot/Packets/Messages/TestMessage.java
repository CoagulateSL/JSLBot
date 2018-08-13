package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class TestMessage extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 1; }
	public final String getName() { return "TestMessage"; }
	@Sequence(0)
	public TestMessage_bTestBlock1 btestblock1=new TestMessage_bTestBlock1();
	@Sequence(1)
	public TestMessage_bNeighborBlock bneighborblock[]=new TestMessage_bNeighborBlock[4];
}
