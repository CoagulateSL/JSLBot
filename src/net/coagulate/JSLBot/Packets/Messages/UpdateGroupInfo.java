package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UpdateGroupInfo extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 341; }
	public final String getName() { return "UpdateGroupInfo"; }
	@Sequence(0)
	public UpdateGroupInfo_bAgentData bagentdata=new UpdateGroupInfo_bAgentData();
	@Sequence(1)
	public UpdateGroupInfo_bGroupData bgroupdata=new UpdateGroupInfo_bGroupData();
}
