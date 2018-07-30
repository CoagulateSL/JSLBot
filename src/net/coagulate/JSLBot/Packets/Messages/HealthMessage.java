package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class HealthMessage extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 138; }
	public final String getName() { return "HealthMessage"; }
	@Sequence(0)
	public HealthMessage_bHealthData bhealthdata=new HealthMessage_bHealthData();
}
