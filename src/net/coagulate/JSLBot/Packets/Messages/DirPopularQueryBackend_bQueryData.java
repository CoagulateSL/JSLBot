package net.coagulate.JSLBot.Packets.Messages;

import net.coagulate.JSLBot.Packets.Block;
import net.coagulate.JSLBot.Packets.Sequence;
import net.coagulate.JSLBot.Packets.Types.BOOL;
import net.coagulate.JSLBot.Packets.Types.LLUUID;
import net.coagulate.JSLBot.Packets.Types.U32;

import javax.annotation.Nonnull;

public class DirPopularQueryBackend_bQueryData extends Block {
	@Nonnull
    @Sequence(0)
	public LLUUID vqueryid=new LLUUID();
	@Nonnull
    @Sequence(1)
	public U32 vqueryflags=new U32();
	@Nonnull
    @Sequence(2)
	public U32 vestateid=new U32();
	@Nonnull
    @Sequence(3)
	public BOOL vgodlike=new BOOL();
}
