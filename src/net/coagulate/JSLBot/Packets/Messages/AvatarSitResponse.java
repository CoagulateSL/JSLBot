package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Frequency;
import net.coagulate.JSLBot.Packets.Message;
import net.coagulate.JSLBot.Packets.Sequence;

import javax.annotation.Nonnull;

public class AvatarSitResponse extends Block implements Message {
	public final int getFrequency() { return Frequency.HIGH; }
	public final int getId() { return 21; }
	@Nonnull
    public final String getName() { return "AvatarSitResponse"; }
	@Nonnull
    @Sequence(0)
	public AvatarSitResponse_bSitObject bsitobject=new AvatarSitResponse_bSitObject();
	@Nonnull
    @Sequence(1)
	public AvatarSitResponse_bSitTransform bsittransform=new AvatarSitResponse_bSitTransform();
}
