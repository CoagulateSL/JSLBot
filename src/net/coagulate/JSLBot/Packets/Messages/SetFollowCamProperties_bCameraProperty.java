package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.F32;
import net.coagulate.JSLBot.Packets.Types.S32;

import javax.annotation.Nonnull;

public class SetFollowCamProperties_bCameraProperty extends Block {
	@Nonnull
    @Sequence(0)
	public S32 vtype=new S32();
	@Nonnull
    @Sequence(1)
	public F32 vvalue=new F32();
}
