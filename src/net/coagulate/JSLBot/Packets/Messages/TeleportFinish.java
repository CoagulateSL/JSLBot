package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class TeleportFinish extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 69; }
	public final String getName() { return "TeleportFinish"; }
	@Sequence(0)
	public TeleportFinish_bInfo binfo=new TeleportFinish_bInfo();
}
