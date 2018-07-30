package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class FreezeUser extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 168; }
	public final String getName() { return "FreezeUser"; }
	@Sequence(0)
	public FreezeUser_bAgentData bagentdata=new FreezeUser_bAgentData();
	@Sequence(1)
	public FreezeUser_bData bdata=new FreezeUser_bData();
}
