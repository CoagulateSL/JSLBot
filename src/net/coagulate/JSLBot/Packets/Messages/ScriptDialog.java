package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class ScriptDialog extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 190; }
	@Nonnull
    public final String getName() { return "ScriptDialog"; }
	@Nonnull
    @Sequence(0)
	public ScriptDialog_bData bdata=new ScriptDialog_bData();
	@Sequence(1)
	public List<ScriptDialog_bButtons> bbuttons;
	@Sequence(2)
	public List<ScriptDialog_bOwnerData> bownerdata;
}
