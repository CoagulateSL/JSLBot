package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class TeleportLocal extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 64; }
	@Nonnull
    public final String getName() { return "TeleportLocal"; }
	@Nonnull
    @Sequence(0)
	public TeleportLocal_bInfo binfo=new TeleportLocal_bInfo();
}
