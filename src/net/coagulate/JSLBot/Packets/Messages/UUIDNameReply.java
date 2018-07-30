package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UUIDNameReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 236; }
	public final String getName() { return "UUIDNameReply"; }
	@Sequence(0)
	public List<UUIDNameReply_bUUIDNameBlock> buuidnameblock;
}
