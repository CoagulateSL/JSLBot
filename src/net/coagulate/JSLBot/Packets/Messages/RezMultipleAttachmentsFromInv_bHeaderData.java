package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U8;

import javax.annotation.Nonnull;

public class RezMultipleAttachmentsFromInv_bHeaderData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vcompoundmsgid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public U8 vtotalobjects=new U8();
	@Nonnull
    @Sequence(2)
	public BOOL vfirstdetachall=new BOOL();
}
