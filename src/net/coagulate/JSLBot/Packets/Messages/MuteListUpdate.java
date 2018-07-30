package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class MuteListUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 318; }
	public final String getName() { return "MuteListUpdate"; }
	@Sequence(0)
	public MuteListUpdate_bMuteData bmutedata=new MuteListUpdate_bMuteData();
}
