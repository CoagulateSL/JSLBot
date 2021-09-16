package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.*;

import javax.annotation.Nonnull;

public class RequestImage_bRequestImage extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vimage=new LLUUID();
	@Nonnull
    @Sequence(1)
	public S8 vdiscardlevel=new S8();
	@Nonnull
    @Sequence(2)
	public F32 vdownloadpriority=new F32();
	@Nonnull
    @Sequence(3)
	public U32 vpacket=new U32();
	@Nonnull
    @Sequence(4)
	public U8 vtype=new U8();
}
