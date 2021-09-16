package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ScriptTeleportRequest extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 195; }
	@Nonnull
    public final String getName() { return "ScriptTeleportRequest"; }
	@Nonnull
    @Sequence(0)
	public ScriptTeleportRequest_bData bdata=new ScriptTeleportRequest_bData();
}
