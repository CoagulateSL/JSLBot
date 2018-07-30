package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GetScriptRunning extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 243; }
	public final String getName() { return "GetScriptRunning"; }
	@Sequence(0)
	public GetScriptRunning_bScript bscript=new GetScriptRunning_bScript();
}
