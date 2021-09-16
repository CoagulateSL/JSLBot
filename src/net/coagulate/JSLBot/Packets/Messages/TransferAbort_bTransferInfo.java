package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.S32;

import javax.annotation.Nonnull;

public class TransferAbort_bTransferInfo extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vtransferid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public S32 vchanneltype=new S32();
}
