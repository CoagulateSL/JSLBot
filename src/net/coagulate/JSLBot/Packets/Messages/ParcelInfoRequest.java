package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ParcelInfoRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 54; }
	public final String getName() { return "ParcelInfoRequest"; }
	@Sequence(0)
	public ParcelInfoRequest_bAgentData bagentdata=new ParcelInfoRequest_bAgentData();
	@Sequence(1)
	public ParcelInfoRequest_bData bdata=new ParcelInfoRequest_bData();
}
