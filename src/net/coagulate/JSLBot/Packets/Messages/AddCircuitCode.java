package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class AddCircuitCode extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 2; }
	public final String getName() { return "AddCircuitCode"; }
	@Sequence(0)
	public AddCircuitCode_bCircuitCode bcircuitcode=new AddCircuitCode_bCircuitCode();
}
