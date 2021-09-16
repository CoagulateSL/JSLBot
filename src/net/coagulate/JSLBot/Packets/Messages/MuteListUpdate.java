package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class MuteListUpdate extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 318; }
	@Nonnull
    public final String getName() { return "MuteListUpdate"; }
	@Nonnull
    @Sequence(0)
	public MuteListUpdate_bMuteData bmutedata=new MuteListUpdate_bMuteData();
}
