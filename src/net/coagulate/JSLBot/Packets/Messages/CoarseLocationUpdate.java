package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class CoarseLocationUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.MEDIUM; }
	public final int getId() { return 6; }
	public final String getName() { return "CoarseLocationUpdate"; }
	@Sequence(0)
	public List<CoarseLocationUpdate_bLocation> blocation;
	@Sequence(1)
	public CoarseLocationUpdate_bIndex bindex=new CoarseLocationUpdate_bIndex();
	@Sequence(2)
	public List<CoarseLocationUpdate_bAgentData> bagentdata;
}
