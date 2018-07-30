package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GroupProfileRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 351; }
	public final String getName() { return "GroupProfileRequest"; }
	@Sequence(0)
	public GroupProfileRequest_bAgentData bagentdata=new GroupProfileRequest_bAgentData();
	@Sequence(1)
	public GroupProfileRequest_bGroupData bgroupdata=new GroupProfileRequest_bGroupData();
}
