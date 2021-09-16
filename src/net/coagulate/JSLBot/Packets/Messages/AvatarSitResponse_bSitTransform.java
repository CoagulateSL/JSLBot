package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;
import net.coagulate.JSLBot.Packets.Types.LLQuaternion;
import net.coagulate.JSLBot.Packets.Types.LLVector3;

import javax.annotation.Nonnull;

public class AvatarSitResponse_bSitTransform extends Block {
	@Nonnull
    @Sequence(0)
	public BOOL vautopilot=new BOOL();
	@Nonnull
    @Sequence(1)
	public LLVector3 vsitposition=new LLVector3();
	@Nonnull
    @Sequence(2)
	public LLQuaternion vsitrotation=new LLQuaternion();
	@Nonnull
    @Sequence(3)
	public LLVector3 vcameraeyeoffset=new LLVector3();
	@Nonnull
    @Sequence(4)
	public LLVector3 vcameraatoffset=new LLVector3();
	@Nonnull
    @Sequence(5)
	public BOOL vforcemouselook=new BOOL();
}
