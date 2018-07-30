package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UUIDNameRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 235; }
	public final String getName() { return "UUIDNameRequest"; }
	@Sequence(0)
	public List<UUIDNameRequest_bUUIDNameBlock> buuidnameblock;
}
