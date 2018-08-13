package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ScriptDataRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 337; }
	public final String getName() { return "ScriptDataRequest"; }
	@Sequence(0)
	public List<ScriptDataRequest_bDataBlock> bdatablock;
}
