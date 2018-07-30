package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DataHomeLocationReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 68; }
	public final String getName() { return "DataHomeLocationReply"; }
	@Sequence(0)
	public DataHomeLocationReply_bInfo binfo=new DataHomeLocationReply_bInfo();
}
