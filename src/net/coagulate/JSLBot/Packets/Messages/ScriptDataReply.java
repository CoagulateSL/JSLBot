package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ScriptDataReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 338; }
	public final String getName() { return "ScriptDataReply"; }
	@Sequence(0)
	public List<ScriptDataReply_bDataBlock> bdatablock;
}
