package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ClearFollowCamProperties extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 160; }
	public final String getName() { return "ClearFollowCamProperties"; }
	@Sequence(0)
	public ClearFollowCamProperties_bObjectData bobjectdata=new ClearFollowCamProperties_bObjectData();
}
