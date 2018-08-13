package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class LogDwellTime extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 18; }
	public final String getName() { return "LogDwellTime"; }
	@Sequence(0)
	public LogDwellTime_bDwellInfo bdwellinfo=new LogDwellTime_bDwellInfo();
}
