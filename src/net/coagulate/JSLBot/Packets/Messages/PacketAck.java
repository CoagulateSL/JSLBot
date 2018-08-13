package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class PacketAck extends Block implements Message {
	public final int getFrequency() { return Frequency.FIXED; }
	public final int getId() { return 0xFFFFFFFB; }
	public final String getName() { return "PacketAck"; }
	@Sequence(0)
	public List<PacketAck_bPackets> bpackets;
}
