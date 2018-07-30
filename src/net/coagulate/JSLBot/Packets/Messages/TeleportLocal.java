package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class TeleportLocal extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 64; }
	public final String getName() { return "TeleportLocal"; }
	@Sequence(0)
	public TeleportLocal_bInfo binfo=new TeleportLocal_bInfo();
}
