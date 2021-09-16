package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ScriptMailRegistration extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 418; }
	@Nonnull
    public final String getName() { return "ScriptMailRegistration"; }
	@Nonnull
    @Sequence(0)
	public ScriptMailRegistration_bDataBlock bdatablock=new ScriptMailRegistration_bDataBlock();
}
