package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ViewerStartAuction extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 228; }
	public final String getName() { return "ViewerStartAuction"; }
	@Sequence(0)
	public ViewerStartAuction_bAgentData bagentdata=new ViewerStartAuction_bAgentData();
	@Sequence(1)
	public ViewerStartAuction_bParcelData bparceldata=new ViewerStartAuction_bParcelData();
}
