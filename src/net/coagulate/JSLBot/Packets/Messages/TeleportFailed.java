package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class TeleportFailed extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 74; }
	public final String getName() { return "TeleportFailed"; }
	@Sequence(0)
	public TeleportFailed_bInfo binfo=new TeleportFailed_bInfo();
	@Sequence(1)
	public List<TeleportFailed_bAlertInfo> balertinfo;
}
