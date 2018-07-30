package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class FormFriendship extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 299; }
	public final String getName() { return "FormFriendship"; }
	@Sequence(0)
	public FormFriendship_bAgentBlock bagentblock=new FormFriendship_bAgentBlock();
}
