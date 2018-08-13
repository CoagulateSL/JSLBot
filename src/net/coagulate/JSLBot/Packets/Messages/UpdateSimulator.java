package net.coagulate.JSLBot.Packets.Messages;
import java.util.*;
import net.coagulate.JSLBot.JSLBot;
import net.coagulate.JSLBot.Packets.*;
import net.coagulate.JSLBot.Packets.Types.*;
public class UpdateSimulator extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 17; }
	public final String getName() { return "UpdateSimulator"; }
	@Sequence(0)
	public UpdateSimulator_bSimulatorInfo bsimulatorinfo=new UpdateSimulator_bSimulatorInfo();
}
