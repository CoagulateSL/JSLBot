package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DirPlacesQuery extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 33; }
	public final String getName() { return "DirPlacesQuery"; }
	@Sequence(0)
	public DirPlacesQuery_bAgentData bagentdata=new DirPlacesQuery_bAgentData();
	@Sequence(1)
	public DirPlacesQuery_bQueryData bquerydata=new DirPlacesQuery_bQueryData();
}
