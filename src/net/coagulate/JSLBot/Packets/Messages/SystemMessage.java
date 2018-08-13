package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SystemMessage extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 404; }
	public final String getName() { return "SystemMessage"; }
	@Sequence(0)
	public SystemMessage_bMethodData bmethoddata=new SystemMessage_bMethodData();
	@Sequence(1)
	public List<SystemMessage_bParamList> bparamlist;
}
