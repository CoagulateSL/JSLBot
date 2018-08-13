package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class SetStartLocation extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 325; }
	public final String getName() { return "SetStartLocation"; }
	@Sequence(0)
	public SetStartLocation_bStartLocationData bstartlocationdata=new SetStartLocation_bStartLocationData();
}
