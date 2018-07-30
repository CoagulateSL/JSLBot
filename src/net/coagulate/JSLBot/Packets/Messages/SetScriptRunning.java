package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SetScriptRunning extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 245; }
	public final String getName() { return "SetScriptRunning"; }
	@Sequence(0)
	public SetScriptRunning_bAgentData bagentdata=new SetScriptRunning_bAgentData();
	@Sequence(1)
	public SetScriptRunning_bScript bscript=new SetScriptRunning_bScript();
}
