package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ScriptDialog extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 190; }
	public final String getName() { return "ScriptDialog"; }
	@Sequence(0)
	public ScriptDialog_bData bdata=new ScriptDialog_bData();
	@Sequence(1)
	public List<ScriptDialog_bButtons> bbuttons;
	@Sequence(2)
	public List<ScriptDialog_bOwnerData> bownerdata;
}
