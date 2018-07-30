package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class TeleportLandingStatusChanged extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 147; }
	public final String getName() { return "TeleportLandingStatusChanged"; }
	@Sequence(0)
	public TeleportLandingStatusChanged_bRegionData bregiondata=new TeleportLandingStatusChanged_bRegionData();
}
