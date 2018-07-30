package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RemoveNameValuePair extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 330; }
	public final String getName() { return "RemoveNameValuePair"; }
	@Sequence(0)
	public RemoveNameValuePair_bTaskData btaskdata=new RemoveNameValuePair_bTaskData();
	@Sequence(1)
	public List<RemoveNameValuePair_bNameValueData> bnamevaluedata;
}
