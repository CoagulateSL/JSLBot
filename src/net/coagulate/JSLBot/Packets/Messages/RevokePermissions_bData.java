package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U32;

import javax.annotation.Nonnull;

public class RevokePermissions_bData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vobjectid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public U32 vobjectpermissions=new U32();
}
