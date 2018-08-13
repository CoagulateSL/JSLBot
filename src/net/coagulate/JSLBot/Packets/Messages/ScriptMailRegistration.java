package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ScriptMailRegistration extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 418; }
	public final String getName() { return "ScriptMailRegistration"; }
	@Sequence(0)
	public ScriptMailRegistration_bDataBlock bdatablock=new ScriptMailRegistration_bDataBlock();
}
