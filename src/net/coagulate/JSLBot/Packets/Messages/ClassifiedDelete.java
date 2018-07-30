package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ClassifiedDelete extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 46; }
	public final String getName() { return "ClassifiedDelete"; }
	@Sequence(0)
	public ClassifiedDelete_bAgentData bagentdata=new ClassifiedDelete_bAgentData();
	@Sequence(1)
	public ClassifiedDelete_bData bdata=new ClassifiedDelete_bData();
}
