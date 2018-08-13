package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ScriptSensorRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 247; }
	public final String getName() { return "ScriptSensorRequest"; }
	@Sequence(0)
	public ScriptSensorRequest_bRequester brequester=new ScriptSensorRequest_bRequester();
}
