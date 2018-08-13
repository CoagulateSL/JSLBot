package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ScriptSensorReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 248; }
	public final String getName() { return "ScriptSensorReply"; }
	@Sequence(0)
	public ScriptSensorReply_bRequester brequester=new ScriptSensorReply_bRequester();
	@Sequence(1)
	public List<ScriptSensorReply_bSensedData> bsenseddata;
}
