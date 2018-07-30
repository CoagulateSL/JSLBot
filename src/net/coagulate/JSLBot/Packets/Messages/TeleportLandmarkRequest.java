package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class TeleportLandmarkRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 65; }
	public final String getName() { return "TeleportLandmarkRequest"; }
	@Sequence(0)
	public TeleportLandmarkRequest_bInfo binfo=new TeleportLandmarkRequest_bInfo();
}
