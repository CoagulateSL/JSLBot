package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class GetScriptRunning extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 243; }
	@Nonnull
    public final String getName() { return "GetScriptRunning"; }
	@Nonnull
    @Sequence(0)
	public GetScriptRunning_bScript bscript=new GetScriptRunning_bScript();
}
