package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UUIDGroupNameReply extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 238; }
	public final String getName() { return "UUIDGroupNameReply"; }
	@Sequence(0)
	public List<UUIDGroupNameReply_bUUIDNameBlock> buuidnameblock;
}
