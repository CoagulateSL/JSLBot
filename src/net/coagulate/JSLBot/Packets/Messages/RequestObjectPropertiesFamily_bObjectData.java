package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U32;

import javax.annotation.Nonnull;

public class RequestObjectPropertiesFamily_bObjectData extends Block {
	@Nonnull
    @Sequence(0)
	public U32 vrequestflags=new U32();
	@Nonnull
    @Sequence(1)
	public LLUUID vobjectid=new LLUUID();
}
