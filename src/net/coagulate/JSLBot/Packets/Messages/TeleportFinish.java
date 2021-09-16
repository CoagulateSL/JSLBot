package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class TeleportFinish extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 69; }
	@Nonnull
    public final String getName() { return "TeleportFinish"; }
	@Nonnull
    @Sequence(0)
	public TeleportFinish_bInfo binfo=new TeleportFinish_bInfo();
}
