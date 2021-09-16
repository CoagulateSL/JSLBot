package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class AtomicPassObject extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 28; }
	@Nonnull
    public final String getName() { return "AtomicPassObject"; }
	@Nonnull
    @Sequence(0)
	public AtomicPassObject_bTaskData btaskdata=new AtomicPassObject_bTaskData();
}
