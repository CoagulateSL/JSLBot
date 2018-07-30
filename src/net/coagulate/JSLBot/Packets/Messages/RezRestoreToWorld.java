package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class RezRestoreToWorld extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 425; }
	public final String getName() { return "RezRestoreToWorld"; }
	@Sequence(0)
	public RezRestoreToWorld_bAgentData bagentdata=new RezRestoreToWorld_bAgentData();
	@Sequence(1)
	public RezRestoreToWorld_bInventoryData binventorydata=new RezRestoreToWorld_bInventoryData();
}
