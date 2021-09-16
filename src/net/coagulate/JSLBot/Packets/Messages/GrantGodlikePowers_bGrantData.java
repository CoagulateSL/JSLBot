package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U8;

import javax.annotation.Nonnull;

public class GrantGodlikePowers_bGrantData extends Block {
	@Nonnull
    @Sequence(0)
	public U8 vgodlevel=new U8();
	@Nonnull
    @Sequence(1)
	public LLUUID vtoken=new LLUUID();
}
