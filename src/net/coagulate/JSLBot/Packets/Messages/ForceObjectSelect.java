package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ForceObjectSelect extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 205; }
	public final String getName() { return "ForceObjectSelect"; }
	@Sequence(0)
	public ForceObjectSelect_bHeader bheader=new ForceObjectSelect_bHeader();
	@Sequence(1)
	public List<ForceObjectSelect_bData> bdata;
}
