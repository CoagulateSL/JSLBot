package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UUIDGroupNameRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 237; }
	public final String getName() { return "UUIDGroupNameRequest"; }
	@Sequence(0)
	public List<UUIDGroupNameRequest_bUUIDNameBlock> buuidnameblock;
}
