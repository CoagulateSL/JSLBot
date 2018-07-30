package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UpdateMuteListEntry extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 263; }
	public final String getName() { return "UpdateMuteListEntry"; }
	@Sequence(0)
	public UpdateMuteListEntry_bAgentData bagentdata=new UpdateMuteListEntry_bAgentData();
	@Sequence(1)
	public UpdateMuteListEntry_bMuteData bmutedata=new UpdateMuteListEntry_bMuteData();
}
