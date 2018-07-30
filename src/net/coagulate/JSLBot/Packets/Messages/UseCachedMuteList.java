package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UseCachedMuteList extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 319; }
	public final String getName() { return "UseCachedMuteList"; }
	@Sequence(0)
	public UseCachedMuteList_bAgentData bagentdata=new UseCachedMuteList_bAgentData();
}
