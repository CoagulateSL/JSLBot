package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class ScriptQuestion extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 188; }
	@Nonnull
    public final String getName() { return "ScriptQuestion"; }
	@Nonnull
    @Sequence(0)
	public ScriptQuestion_bData bdata=new ScriptQuestion_bData();
	@Nonnull
    @Sequence(1)
	public ScriptQuestion_bExperience bexperience=new ScriptQuestion_bExperience();
}
