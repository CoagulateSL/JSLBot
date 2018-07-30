package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ScriptQuestion extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 188; }
	public final String getName() { return "ScriptQuestion"; }
	@Sequence(0)
	public ScriptQuestion_bData bdata=new ScriptQuestion_bData();
}
