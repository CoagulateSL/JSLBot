package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.S32;
import net.coagulate.JSLBot.Packets.Types.S8;

import javax.annotation.Nonnull;

public class LogParcelChanges_bParcelData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vparcelid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public LLUUID vownerid=new LLUUID();
	@Nonnull
    @Sequence(2)
	public BOOL visownergroup=new BOOL();
	@Nonnull
    @Sequence(3)
	public S32 vactualarea=new S32();
	@Nonnull
    @Sequence(4)
	public S8 vaction=new S8();
	@Nonnull
    @Sequence(5)
	public LLUUID vtransactionid=new LLUUID();
}
