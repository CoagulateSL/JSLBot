package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GenericMessage extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 261; }
	public final String getName() { return "GenericMessage"; }
	@Sequence(0)
	public GenericMessage_bAgentData bagentdata=new GenericMessage_bAgentData();
	@Sequence(1)
	public GenericMessage_bMethodData bmethoddata=new GenericMessage_bMethodData();
	@Sequence(2)
	public List<GenericMessage_bParamList> bparamlist;
}
