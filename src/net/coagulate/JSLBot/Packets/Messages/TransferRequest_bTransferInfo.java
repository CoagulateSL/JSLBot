package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.F32;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.S32;
import net.coagulate.JSLBot.Packets.Types.Variable2;

import javax.annotation.Nonnull;

public class TransferRequest_bTransferInfo extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vtransferid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public S32 vchanneltype=new S32();
	@Nonnull
    @Sequence(2)
	public S32 vsourcetype=new S32();
	@Nonnull
    @Sequence(3)
	public F32 vpriority=new F32();
	@Nonnull
    @Sequence(4)
	public Variable2 vparams=new Variable2();
}
