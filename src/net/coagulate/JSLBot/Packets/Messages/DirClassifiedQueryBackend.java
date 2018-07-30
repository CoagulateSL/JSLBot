package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class DirClassifiedQueryBackend extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 40; }
	public final String getName() { return "DirClassifiedQueryBackend"; }
	@Sequence(0)
	public DirClassifiedQueryBackend_bAgentData bagentdata=new DirClassifiedQueryBackend_bAgentData();
	@Sequence(1)
	public DirClassifiedQueryBackend_bQueryData bquerydata=new DirClassifiedQueryBackend_bQueryData();
}
