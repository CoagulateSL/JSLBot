package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class StartAuction extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 229; }
	public final String getName() { return "StartAuction"; }
	@Sequence(0)
	public StartAuction_bAgentData bagentdata=new StartAuction_bAgentData();
	@Sequence(1)
	public StartAuction_bParcelData bparceldata=new StartAuction_bParcelData();
}
