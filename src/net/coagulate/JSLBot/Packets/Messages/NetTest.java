package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class NetTest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 326; }
	public final String getName() { return "NetTest"; }
	@Sequence(0)
	public NetTest_bNetBlock bnetblock=new NetTest_bNetBlock();
}
