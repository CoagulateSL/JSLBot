package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class TeleportStart extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 73; }
	public final String getName() { return "TeleportStart"; }
	@Sequence(0)
	public TeleportStart_bInfo binfo=new TeleportStart_bInfo();
}
