package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.F32;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.S32;

import javax.annotation.Nonnull;

public class ParcelDwellReply_bData extends Block {
	@Nonnull
    @Sequence(0)
	public S32 vlocalid=new S32();
	@Nonnull
    @Sequence(1)
	public LLUUID vparcelid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public F32 vdwell=new F32();
}
