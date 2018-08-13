package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class TeleportCancel extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 72; }
	public final String getName() { return "TeleportCancel"; }
	@Sequence(0)
	public TeleportCancel_bInfo binfo=new TeleportCancel_bInfo();
}
