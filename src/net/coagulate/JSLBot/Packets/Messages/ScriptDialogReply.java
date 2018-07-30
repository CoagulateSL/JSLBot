package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ScriptDialogReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 191; }
	public final String getName() { return "ScriptDialogReply"; }
	@Sequence(0)
	public ScriptDialogReply_bAgentData bagentdata=new ScriptDialogReply_bAgentData();
	@Sequence(1)
	public ScriptDialogReply_bData bdata=new ScriptDialogReply_bData();
}
