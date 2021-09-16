package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class KickUser extends Block implements Message {
	public final int getFrequency() { return Frequency.LOW; }
	public final int getId() { return 163; }
	@Nonnull
    public final String getName() { return "KickUser"; }
	@Nonnull
    @Sequence(0)
	public KickUser_bTargetBlock btargetblock=new KickUser_bTargetBlock();
	@Nonnull
    @Sequence(1)
	public KickUser_bUserInfo buserinfo=new KickUser_bUserInfo();
}
