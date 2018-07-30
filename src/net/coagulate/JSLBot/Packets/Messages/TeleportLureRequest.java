package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class TeleportLureRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 71; }
	public final String getName() { return "TeleportLureRequest"; }
	@Sequence(0)
	public TeleportLureRequest_bInfo binfo=new TeleportLureRequest_bInfo();
}
