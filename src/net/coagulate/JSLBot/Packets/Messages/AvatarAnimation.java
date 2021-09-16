package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;
import java.util.List;

public class AvatarAnimation extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 20; }
	@Nonnull
    public final String getName() { return "AvatarAnimation"; }
	@Nonnull
    @Sequence(0)
	public AvatarAnimation_bSender bsender=new AvatarAnimation_bSender();
	@Sequence(1)
	public List<AvatarAnimation_bAnimationList> banimationlist;
	@Sequence(2)
	public List<AvatarAnimation_bAnimationSourceList> banimationsourcelist;
	@Sequence(3)
	public List<AvatarAnimation_bPhysicalAvatarEventList> bphysicalavatareventlist;
}
