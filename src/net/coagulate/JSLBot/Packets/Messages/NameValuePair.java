package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class NameValuePair extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 329; }
	public final String getName() { return "NameValuePair"; }
	@Sequence(0)
	public NameValuePair_bTaskData btaskdata=new NameValuePair_bTaskData();
	@Sequence(1)
	public List<NameValuePair_bNameValueData> bnamevaluedata;
}
