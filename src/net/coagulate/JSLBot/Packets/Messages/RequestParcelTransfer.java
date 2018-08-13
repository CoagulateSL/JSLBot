package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RequestParcelTransfer extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 220; }
	public final String getName() { return "RequestParcelTransfer"; }
	@Sequence(0)
	public RequestParcelTransfer_bData bdata=new RequestParcelTransfer_bData();
	@Sequence(1)
	public RequestParcelTransfer_bRegionData bregiondata=new RequestParcelTransfer_bRegionData();
}
