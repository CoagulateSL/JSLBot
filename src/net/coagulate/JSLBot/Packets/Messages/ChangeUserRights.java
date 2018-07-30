package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ChangeUserRights extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 321; }
	public final String getName() { return "ChangeUserRights"; }
	@Sequence(0)
	public ChangeUserRights_bAgentData bagentdata=new ChangeUserRights_bAgentData();
	@Sequence(1)
	public List<ChangeUserRights_bRights> brights;
}
