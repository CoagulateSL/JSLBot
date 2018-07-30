package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DirClassifiedQuery extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 39; }
	public final String getName() { return "DirClassifiedQuery"; }
	@Sequence(0)
	public DirClassifiedQuery_bAgentData bagentdata=new DirClassifiedQuery_bAgentData();
	@Sequence(1)
	public DirClassifiedQuery_bQueryData bquerydata=new DirClassifiedQuery_bQueryData();
}
