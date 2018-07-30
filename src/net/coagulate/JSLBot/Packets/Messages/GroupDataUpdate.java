package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupDataUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 388; }
	public final String getName() { return "GroupDataUpdate"; }
	@Sequence(0)
	public List<GroupDataUpdate_bAgentGroupData> bagentgroupdata;
}
