package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class TerminateFriendship extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 300; }
	public final String getName() { return "TerminateFriendship"; }
	@Sequence(0)
	public TerminateFriendship_bAgentData bagentdata=new TerminateFriendship_bAgentData();
	@Sequence(1)
	public TerminateFriendship_bExBlock bexblock=new TerminateFriendship_bExBlock();
}
