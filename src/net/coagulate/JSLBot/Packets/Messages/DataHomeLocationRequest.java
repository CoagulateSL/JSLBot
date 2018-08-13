package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DataHomeLocationRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 67; }
	public final String getName() { return "DataHomeLocationRequest"; }
	@Sequence(0)
	public DataHomeLocationRequest_bInfo binfo=new DataHomeLocationRequest_bInfo();
	@Sequence(1)
	public DataHomeLocationRequest_bAgentInfo bagentinfo=new DataHomeLocationRequest_bAgentInfo();
}
