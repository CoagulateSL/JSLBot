package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SetCPURatio extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 327; }
	public final String getName() { return "SetCPURatio"; }
	@Sequence(0)
	public SetCPURatio_bData bdata=new SetCPURatio_bData();
}
