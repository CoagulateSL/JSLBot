package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ScriptRunningReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 244; }
	public final String getName() { return "ScriptRunningReply"; }
	@Sequence(0)
	public ScriptRunningReply_bScript bscript=new ScriptRunningReply_bScript();
}
