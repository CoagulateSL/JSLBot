package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class ConfirmEnableSimulator extends Block implements Message {
	public final int getFrequency() { return Frequency.MEDIUM; }
	public final int getId() { return 8; }
	public final String getName() { return "ConfirmEnableSimulator"; }
	@Sequence(0)
	public ConfirmEnableSimulator_bAgentData bagentdata=new ConfirmEnableSimulator_bAgentData();
}
