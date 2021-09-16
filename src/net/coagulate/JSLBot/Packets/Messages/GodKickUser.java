package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class GodKickUser extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 165; }
	@Nonnull
    public final String getName() { return "GodKickUser"; }
	@Nonnull
    @Sequence(0)
	public GodKickUser_bUserInfo buserinfo=new GodKickUser_bUserInfo();
}
