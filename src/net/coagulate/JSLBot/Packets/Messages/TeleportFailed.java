package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class TeleportFailed extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 74; }
	@Nonnull
    public final String getName() { return "TeleportFailed"; }
	@Nonnull
    @Sequence(0)
	public TeleportFailed_bInfo binfo=new TeleportFailed_bInfo();
	@Sequence(1)
	public List<TeleportFailed_bAlertInfo> balertinfo;
}
