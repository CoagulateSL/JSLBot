package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLVector4;

import javax.annotation.Nonnull;

public class CameraConstraint_bCameraCollidePlane extends Block {
	@Nonnull
    @Sequence(0)
	public LLVector4 vplane=new LLVector4();
}
