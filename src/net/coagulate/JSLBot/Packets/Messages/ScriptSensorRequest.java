package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ScriptSensorRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 247; }
	@Nonnull
    public final String getName() { return "ScriptSensorRequest"; }
	@Nonnull
    @Sequence(0)
	public ScriptSensorRequest_bRequester brequester=new ScriptSensorRequest_bRequester();
}
