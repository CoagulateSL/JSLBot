package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class UpdateSimulator extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 17; }
	@Nonnull
    public final String getName() { return "UpdateSimulator"; }
	@Nonnull
    @Sequence(0)
	public UpdateSimulator_bSimulatorInfo bsimulatorinfo=new UpdateSimulator_bSimulatorInfo();
}
