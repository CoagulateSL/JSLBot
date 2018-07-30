package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class GodlikeMessage extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 259; }
	public final String getName() { return "GodlikeMessage"; }
	@Sequence(0)
	public GodlikeMessage_bAgentData bagentdata=new GodlikeMessage_bAgentData();
	@Sequence(1)
	public GodlikeMessage_bMethodData bmethoddata=new GodlikeMessage_bMethodData();
	@Sequence(2)
	public List<GodlikeMessage_bParamList> bparamlist;
}
