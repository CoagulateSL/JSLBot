package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class LandStatReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 422; }
	public final String getName() { return "LandStatReply"; }
	@Sequence(0)
	public LandStatReply_bRequestData brequestdata=new LandStatReply_bRequestData();
	@Sequence(1)
	public List<LandStatReply_bReportData> breportdata;
}
