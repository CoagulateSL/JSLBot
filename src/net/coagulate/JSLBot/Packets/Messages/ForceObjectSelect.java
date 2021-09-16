package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ForceObjectSelect extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 205; }
	@Nonnull
    public final String getName() { return "ForceObjectSelect"; }
	@Nonnull
    @Sequence(0)
	public ForceObjectSelect_bHeader bheader=new ForceObjectSelect_bHeader();
	@Sequence(1)
	public List<ForceObjectSelect_bData> bdata;
}
