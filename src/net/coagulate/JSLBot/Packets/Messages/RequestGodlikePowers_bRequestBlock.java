package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;
import net.coagulate.JSLBot.Packets.Types.LLUUID;

import javax.annotation.Nonnull;

public class RequestGodlikePowers_bRequestBlock extends Block {
	@Nonnull
    @Sequence(0)
	public BOOL vgodlike=new BOOL();
	@Nonnull
    @Sequence(1)
	public LLUUID vtoken=new LLUUID();
}
