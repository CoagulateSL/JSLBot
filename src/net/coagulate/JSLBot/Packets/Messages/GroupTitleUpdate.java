package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupTitleUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 377; }
	public final String getName() { return "GroupTitleUpdate"; }
	@Sequence(0)
	public GroupTitleUpdate_bAgentData bagentdata=new GroupTitleUpdate_bAgentData();
}
