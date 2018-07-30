package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DirPlacesQueryBackend extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 34; }
	public final String getName() { return "DirPlacesQueryBackend"; }
	@Sequence(0)
	public DirPlacesQueryBackend_bAgentData bagentdata=new DirPlacesQueryBackend_bAgentData();
	@Sequence(1)
	public DirPlacesQueryBackend_bQueryData bquerydata=new DirPlacesQueryBackend_bQueryData();
}
